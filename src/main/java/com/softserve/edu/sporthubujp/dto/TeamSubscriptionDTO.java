package com.softserve.edu.sporthubujp.dto;
import lombok.Data;

@Data
public class TeamSubscriptionDTO {
    private String id;
    private String name;
    private String location;
    private Byte logo;
    private String description;
    private CategoryDTO category;
    private String subscriptionId;

    public TeamSubscriptionDTO(TeamDTO teamsDTO, String subscription) {
        this.id = teamsDTO.getId();
        this.name = teamsDTO.getName();
        this.location = teamsDTO.getLocation();
        this.logo = teamsDTO.getLogo();
        this.description = teamsDTO.getDescription();
        this.category = teamsDTO.getCategory();
        this.subscriptionId = subscription;
    }
}