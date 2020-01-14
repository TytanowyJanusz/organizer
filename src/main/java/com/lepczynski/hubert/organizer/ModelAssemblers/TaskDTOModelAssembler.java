package com.lepczynski.hubert.organizer.ModelAssemblers;


import com.lepczynski.hubert.organizer.Controllers.TaskController;
import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;



@Component
public class TaskDTOModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskDTO>
{
    private final ModelMapper modelMapper;
    private final TaskController taskController;
    private final LinkRelationProvider relLinkProvider;

    @Autowired
    TaskDTOModelAssembler(ModelMapper modelMapper, TaskController taskController, LinkRelationProvider relLinkProvider)
    {
        super(TaskController.class, TaskDTO.class);
        this.modelMapper = modelMapper;
        this.taskController = taskController;
        this.relLinkProvider = relLinkProvider;
    }

    @Override

    public TaskDTO toModel(Task task)
    {
        TaskDTO taskDTO= modelMapper.map(task, TaskDTO.class);

        taskDTO.add(Link)

        return taskDTO;
    }

}
