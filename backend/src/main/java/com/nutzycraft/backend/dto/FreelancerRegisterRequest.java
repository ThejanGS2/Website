package com.nutzycraft.backend.dto;

import lombok.Data;

@Data
public class FreelancerRegisterRequest {
    private String fullName;
    private String email;
    private String password;
}
