package com.example.parking_meter.dto;

import jakarta.validation.constraints.NotNull;

public record CreatePriceDto(
        @NotNull(message = "Você deve enviar um valor possivel para o preço")
        Number price
) {
}
