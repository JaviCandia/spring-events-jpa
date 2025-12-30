package com.javiersillo.eventosapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventResponse {
    private Long id;
    private String name;
    private LocalDate date;
    private String location;
}
