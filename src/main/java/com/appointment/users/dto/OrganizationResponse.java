package com.appointment.users.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrganizationResponse {
    private Long orgId;
    private String name;
    private String location;
}
