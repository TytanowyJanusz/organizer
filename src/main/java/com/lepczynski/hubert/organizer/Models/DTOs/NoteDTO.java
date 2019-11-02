package com.lepczynski.hubert.organizer.Models.DTOs;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NoteDTO
{
    Long id;
    String title;
    String content;
    int importance;
    LocalDateTime timeOfCreation;
    Long userId;
}
