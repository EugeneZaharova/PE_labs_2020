package ru.eugene.backend;

import ru.eugene.backend.dto.Voucher;

import java.util.UUID;

public class ObjectGenerator {
    public static Voucher generateVoucher() {
        return Voucher.builder()
                .id(UUID.randomUUID())
                .name("Voucher")
                .description("Default trip")
                .price(10000L)
                .destinationCountry("RU")
                .destinationRegion("Moscow")
                .build();
    }
}
