package com.lepczynski.hubert.organizer.Models.DTOs;

import lombok.Data;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class TaskDTO extends RepresentationModel<TaskDTO>
{
    private Long id;
    private String content;
    private int importance;
    private int urgency;
    private boolean isDone;
    private LocalDateTime timeOfCreation;
    private int category;
    private Long userId;
}
