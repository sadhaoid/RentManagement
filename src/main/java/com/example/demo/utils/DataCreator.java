package com.example.demo.utils;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.collections4.ListUtils;

@Component
@AllArgsConstructor
//@RequiredArgsConstructor
public class DataCreator {
    private final LandLordService landLordService;
    private final ApartmentService apartmentService;
    private final TenantService tenantService;
    private final RoomService roomService;
    private final LeaseContractService leaseContractService;

    private final Faker faker = new Faker(new Locale("zh-CN"));
    private final Faker fakerEn = new Faker(new Locale("en-US"));
    private final Random random = new Random();

    static final LocalDateTime START = LocalDateTime.of(2020, 1, 1, 0, 0);
    static final LocalDateTime END = LocalDateTime.of(2025, 12, 31 ,23,59);

//    @PostConstruct
    public void landLordCreator(){
        leaseContractService.truncateLeaseContract();
        roomService.truncateRoom();
        apartmentService.truncateApartment();
        tenantService.truncateTenant();
        landLordService.truncateLandLord();

        for (long i = 1; i < 101; i++) {
            landLordService.addLandLord(LandLordDo.builder()
                    .name(faker.name().fullName())
                    .phone(faker.number().digits(13))
                    .sex(random.nextInt(1,3))
                    .idCard(faker.finance().creditCard())
                    .email(fakerEn.internet().safeEmailAddress())
                    .bankAccount(faker.number().digits(6))
                    .status(1)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }

        for (long i = 1; i < 501; i++) {
            apartmentService.addApartment(ApartmentDo.builder()
                    .landlordId(random.nextLong(100) + 1)
                    .communityName(faker.community().character())
                    .address(faker.address().fullAddress())
                    .city(faker.address().city())
                    .district(fakerEn.address().state())
                    .floor(random.nextInt(1,25))
                    .totalFloor(random.nextInt(25,31))
                    .area(faker.number().randomDouble(2, 40, 200))
                    .roomCount(random.nextInt(1,4))
                    .hallCount(random.nextInt(3))
                    .toiletCount(random.nextInt(1,3))
                    .rentType(random.nextInt(2))
                    .status(1)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }

        for (long i = 1; i < 2001; i++) {
            tenantService.addTenant(TenantDo.builder()
                    .name(faker.name().fullName())
                    .phone(faker.number().digits(13))
                    .sex(random.nextInt(2) + 1)
                    .idCard(faker.finance().creditCard())
                    .emergencyContact(faker.name().fullName())
                    .emergencyPhone(faker.number().digits(13))
                    .status(1)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }

        for (int i = 0; i < 5000; i++) {
            roomService.addRoom(RoomDo.builder()
            .apartmentId(random.nextLong(500) + 1)
            .roomNumber(String.valueOf(random.nextInt(100,1000)))
            .area(faker.number().randomDouble(2, 10, 50))
            .roomType(random.nextInt(1,4))
            .monthlyRent(random.nextDouble(1000, 5000))
            .deposit(500.0)
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .build());
        }
        normalLoopCreator();
        leaseContractService.truncateLeaseContract();
        doubleLoopCreator();
        leaseContractService.truncateLeaseContract();
        apachePartition();

    }

    public void doubleLoopCreator() {
        Long startTime = System.currentTimeMillis();
        for (long i = 0; i < 50; i++) {
            ArrayList<LeaseContractDo> leaseContracts = new ArrayList<>();
            for (int j = 0; j < 2000; j++) {
                leaseContracts.add(LeaseContractDo.builder()
                        .contractNo(Long.valueOf(faker.number().digits(15)))
                        .tenantId(random.nextLong(2000) + 1)
                        .roomId(random.nextLong(5000) + 1)
                        .startDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                        .endDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                        .monthlyRent(faker.number().randomDouble(2, 1000, 5000))
                        .deposit(500.0)
                        .payDay(random.nextInt(1,28))
                        .payCycle(random.nextInt(1,4))
                        .status(random.nextInt(4))
                        .signDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build());
            }
            leaseContractService.saveAll(leaseContracts);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("双循环批量插入耗时: " + (endTime - startTime) + "ms");
    }


    public void apachePartition() {
        Long startTime = System.currentTimeMillis();
        ArrayList<LeaseContractDo> partitionList = new ArrayList<>();
        for (int j = 0; j < 100000; j++) {
            partitionList.add(LeaseContractDo.builder()
                    .contractNo(Long.valueOf(faker.number().digits(15)))
                    .tenantId(random.nextLong(2000) + 1)
                    .roomId(random.nextLong(5000) + 1)
                    .startDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                    .endDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                    .monthlyRent(faker.number().randomDouble(2, 1000, 5000))
                    .deposit(500.0)
                    .payDay(random.nextInt(1,28))
                    .payCycle(random.nextInt(1,4))
                    .status(random.nextInt(4))
                    .signDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }
        ListUtils.partition(partitionList,2000).forEach(leaseContractService::saveAll);
        Long endTime = System.currentTimeMillis();
        System.out.println("分片批量插入耗时: " + (endTime - startTime) + "ms");
    }

    public void normalLoopCreator() {
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            leaseContractService.addLeaseContract(LeaseContractDo.builder()
                    .contractNo(Long.valueOf(faker.number().digits(15)))
                    .tenantId(random.nextLong(2000) + 1)
                    .roomId(random.nextLong(5000) + 1)
                    .startDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                    .endDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                    .monthlyRent(faker.number().randomDouble(2, 1000, 5000))
                    .deposit(500.0)
                    .payDay(random.nextInt(1,28))
                    .payCycle(random.nextInt(1,4))
                    .status(random.nextInt(4))
                    .signDate(LocalDateTime.ofInstant(faker.timeAndDate().between(START.atZone(ZoneId.systemDefault()).toInstant(),END.atZone(ZoneId.systemDefault()).toInstant()), ZoneId.systemDefault()))
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("单条插入耗时: " + (endTime - startTime) + "ms");
    }
}
