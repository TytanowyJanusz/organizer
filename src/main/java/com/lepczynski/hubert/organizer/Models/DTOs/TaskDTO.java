package com.lepczynski.hubert.organizer.Models.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO
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
