package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;
import lombok.Data;
@Data
public class SubscriptionDTO {
    private String id;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private UserDTO user;
    private TeamDTO team;
    private CategoryDTO category;
}
