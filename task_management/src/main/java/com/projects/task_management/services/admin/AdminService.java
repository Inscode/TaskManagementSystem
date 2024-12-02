package com.projects.task_management.services.admin;

import com.projects.task_management.dto.TaskDTO;
import com.projects.task_management.dto.UserDto;

import java.util.List;

public interface AdminService {
    List<UserDto> getUsers();
    TaskDTO createTask(TaskDTO taskDTO);
}
