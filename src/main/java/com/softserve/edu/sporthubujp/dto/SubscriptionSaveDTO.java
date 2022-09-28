package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SubscriptionSaveDTO {
    private String userId;
    private String teamId;
}