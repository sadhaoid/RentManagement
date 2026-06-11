package com.example.demo.service;

import com.example.demo.entity.LeaseContractDo;
import com.example.demo.reposity.LandLordRepository;
import com.example.demo.reposity.LeaseContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LeaseContractService {
    private LeaseContractRepository leaseContractRepository;

    public void truncateLeaseContract() {
        leaseContractRepository.truncateLeaseContract();
    }

    public void addLeaseContract(LeaseContractDo leaseContractdo) {
        // 这里可以添加逻辑来创建租赁合同，例如保存到数据库中
        // 目前只是一个示例方法，具体实现需要根据实际需求来编写
        leaseContractRepository.save(leaseContractdo);
    }

    public void saveAll(List<LeaseContractDo> leaseContractDos) {
        leaseContractRepository.saveAll(leaseContractDos);
    }
}
