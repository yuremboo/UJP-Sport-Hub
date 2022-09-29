package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SubscriptionSaveDTO {
    @NotNull
    private String userId;
    @NotNull
    private String teamId;
}