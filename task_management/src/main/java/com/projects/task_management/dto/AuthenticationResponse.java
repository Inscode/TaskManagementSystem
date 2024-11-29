package com.projects.task_management.dto;

import com.projects.task_management.enums.UserRole;
import lombok.Data;

@Data

public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
}
