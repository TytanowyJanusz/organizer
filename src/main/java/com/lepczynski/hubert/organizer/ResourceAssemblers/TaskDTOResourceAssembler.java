package com.lepczynski.hubert.organizer.ResourceAssemblers;


import com.lepczynski.hubert.organizer.Controllers.TaskController;
import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TaskDTOResourceAssembler implements ResourceAssembler<TaskDTO, Resource<TaskDTO>>
{
    @Override
    public Resource<TaskDTO> toResource(TaskDTO taskDTO)
    {
        return new Resource<>(taskDTO,
                linkTo(methodOn(TaskController.class).getTaskById(taskDTO.getId())).withSelfRel(),
                linkTo(methodOn(TaskController.class).getAllTasks()).withRel("tasks"));
    }
}
