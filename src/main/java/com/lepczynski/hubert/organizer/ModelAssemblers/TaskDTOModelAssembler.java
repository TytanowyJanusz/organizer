package com.lepczynski.hubert.organizer.ModelAssemblers;


import com.lepczynski.hubert.organizer.Controllers.TaskController;
import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;



@Component
public class TaskDTOModelAssembler extends RepresentationModelAssemblerSupport<TaskDTO, EntityModel<TaskDTO>>
{
    TaskDTOModelAssembler()
    {
        super(TaskController.class, EntityModel<TaskDTO>.class);
    }
    @Override
    public EntityModel<TaskDTO> toModel(TaskDTO taskDTO)
    {
        return new EntityModel<TaskDTO>(taskDTO);
    }
}
