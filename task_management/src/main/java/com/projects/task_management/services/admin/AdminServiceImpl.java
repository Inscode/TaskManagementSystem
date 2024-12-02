package com.projects.task_management.services.admin;

import com.projects.task_management.dto.TaskDTO;
import com.projects.task_management.dto.UserDto;
import com.projects.task_management.entities.Task;
import com.projects.task_management.entities.User;
import com.projects.task_management.enums.TaskStatus;
import com.projects.task_management.enums.UserRole;
import com.projects.task_management.repositories.TaskRepository;
import com.projects.task_management.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user->user.getUserRole() == UserRole.EMPLOYEE)
                .map(User::getUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Optional<User> optionalUser = userRepository.findById(taskDTO.getEmployeeId());
        if(optionalUser.isPresent()){
            Task task = new Task();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setPriority(taskDTO.getPriority());
            task.setDueDate(taskDTO.getDueDate());
            task.setTaskStatus(TaskStatus.INPROGRESS);
            task.setUser(optionalUser.get());
            return taskRepository.save(task).getTaskDTO();
        }
        return null;

    }
}
