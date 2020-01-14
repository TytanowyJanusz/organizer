package com.lepczynski.hubert.organizer.ModelAssemblers;


import com.lepczynski.hubert.organizer.Controllers.TaskController;
import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Component
public class TaskDTOModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskDTO>
{
    private final ModelMapper modelMapper;
    private final TaskController taskController;
    private final LinkRelationProvider linkRelProvider;


    @Autowired
    TaskDTOModelAssembler(ModelMapper modelMapper, TaskController taskController, LinkRelationProvider linkRelProvider)
    {
        super(TaskController.class, TaskDTO.class);
        this.modelMapper = modelMapper;
        this.taskController = taskController;
        this.linkRelProvider = linkRelProvider;
    }

    @Override

    public TaskDTO toModel(Task task)
    {
        TaskDTO taskDTO= modelMapper.map(task, TaskDTO.class);

        taskDTO.add(linkTo(TaskController.class).withSelfRel());
        taskDTO.add(linkTo(TaskController.class).withRel(linkRelProvider.getCollectionResourceRelFor(TaskDTO.class)));
        //TODO add links for actions available on Task
        //TODO how to let user navigate back to other parts of app?

        return taskDTO;
    }


}
