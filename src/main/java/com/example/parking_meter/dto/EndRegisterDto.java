package com.example.parking_meter.dto;

public record EndRegisterDto(
        String registerId,
        String vehiclePlate,
        String startDate,
        String endDate,
        String price
) {
}
