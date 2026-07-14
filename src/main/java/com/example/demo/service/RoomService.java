package com.example.demo.service;

import com.example.demo.entity.LeaseContractDo;
import com.example.demo.entity.RoomDo;
import com.example.demo.entity.RoomWithTenantDto;
import com.example.demo.entity.TenantDo;
import com.example.demo.reposity.LeaseContractRepository;
import com.example.demo.reposity.RoomRepository;
import com.example.demo.reposity.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final LeaseContractRepository  leaseContractRepository;

    private final TenantRepository tenantRepository;

    public void addRoom(RoomDo roomDo){
        roomRepository.save(roomDo);
    }

    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }

    public void updateRoom(RoomDo roomDo){
        roomRepository.save(roomDo);
    }

    public RoomDo getRoomById(Long id){
        return roomRepository.findById(id).orElse(null);
    }

    public void truncateRoom(){
        roomRepository.truncateRoom();
    }

    public Map<Long, RoomDo> getRoomMap() {
        return roomRepository.findAll().stream().collect(Collectors.toMap(RoomDo::getId, room -> room));
    }

    public List<RoomWithTenantDto> getRoomWithTenant(Long apartmentId) {
        List<RoomDo> roomByApartmentId = roomRepository.findByApartmentId(apartmentId);
//        List<RoomDo> list = roomByApartmentId.stream().filter(room -> leaseContractRepository.findByRoomIdAndStatus(room.getId(), 1)).toList();
//
//        tenantRepository.findById()

        //todo stream转化
        List<RoomWithTenantDto> resultList = new ArrayList<>();

        for (RoomDo room: roomByApartmentId) {
            List<LeaseContractDo> byRoomIdAndStatus = leaseContractRepository.findByRoomIdAndStatus(room.getId(), 1);
            for (LeaseContractDo leaseContractDo: byRoomIdAndStatus){
                TenantDo byId = tenantRepository.findAllById(leaseContractDo.getTenantId());
                 resultList.add(RoomWithTenantDto
                        .builder()
                        .roomId(room.getId())
                        .apartmentId(room.getApartmentId())
                        .area(room.getArea())
                        .monthlyRent(room.getMonthlyRent())
                        .deposit(room.getDeposit())
                        .name(byId.getName())
                        .phone(byId.getPhone())
                        .build());
            }
        }
        return resultList;
    }
}
