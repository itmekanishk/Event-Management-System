package com.aes.eventmanagementsystem.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.aes.eventmanagementsystem.validations.FutureDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EventDto {

    @NotBlank(message = "Event Name must not be blank")
    private String eventName;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @FutureDate
    private LocalDate eventDate;

    @NotBlank(message = "Event Location must not be blank")
    private String eventLocation;
}
