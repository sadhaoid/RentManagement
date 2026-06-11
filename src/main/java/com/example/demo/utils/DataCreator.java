package com.example.demo.utils;

import com.example.demo.entity.ApartmentDo;
import com.example.demo.entity.LandLordDo;
import com.example.demo.entity.TenantDo;
import com.example.demo.service.ApartmentService;
import com.example.demo.service.LandLordService;
import com.example.demo.service.TenantService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

import static java.lang.Math.random;

@Component
@AllArgsConstructor
public class DataCreator {
    private final LandLordService landLordService;
    private final ApartmentService apartmentService;
    private final TenantService tenantService;

    private final Faker faker = new Faker(new Locale("zh-CN"));
    private final Faker fakerEn = new Faker(new Locale("en-US"));
    private final Random random = new Random();

    @PostConstruct
    public void landLordCreator(){
        for (long i = 1; i < 101; i++) {
            landLordService.addLandLord(LandLordDo.builder()
                    .name(faker.name().fullName())
                    .phone(faker.number().digits(13))
                    .sex(random.nextInt(2) + 1)
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
                    .floor(random.nextInt(25) + 1)
                            .totalFloor(random.nextInt(5) + 25)
                            .area(faker.number().randomDouble(2, 40, 200))
                            .roomCount(random.nextInt(3)+ 1)
                            .hallCount(random.nextInt(3))
                            .toiletCount(random.nextInt(2)+ 1)
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

    }
}
