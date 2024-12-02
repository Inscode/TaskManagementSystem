package com.projects.task_management.Controller.admin;

import com.projects.task_management.dto.TaskDTO;
import com.projects.task_management.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin("*")

public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO){
        TaskDTO createTaskDTO = adminService.createTask(taskDTO);
        if(createTaskDTO == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(createTaskDTO);
    }
}
