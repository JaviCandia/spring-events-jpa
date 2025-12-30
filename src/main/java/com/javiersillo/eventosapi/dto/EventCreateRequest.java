package com.javiersillo.eventosapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventCreateRequest {
    // Solo validamos que los datos de entrada sean correctos
    // Por eso no hacemos validaciones en DTO de salida

    @NotBlank(message = "El nombre del evento no puede estar vacío.")
    private String name;

    @NotNull(message = "La fecha no puede estar vacía.")
    private LocalDate date;

    @NotBlank(message = "La ubicación no puede estar vacía.")
    private String location;
}
