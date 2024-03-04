package com.example.parking_meter.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateRegisterDto(
        @NotBlank(message = "Você deve enviar o id do parking meter id")
        String parkingMeterId,
        @NotBlank(message = "Você deve enviar a placa do veículo")
        String vehiclePlate
) {
}
