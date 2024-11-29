package com.projects.task_management.dto;

import lombok.Data;

@Data

public class AuthenticationRequest {
    private String email;
    private String password;
}
