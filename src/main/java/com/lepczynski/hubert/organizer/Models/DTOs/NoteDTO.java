package com.lepczynski.hubert.organizer.Models.DTOs;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
@Data
public class NoteDTO extends RepresentationModel<NoteDTO>
{
    Long id;
    String title;
    String content;
    int importance;
    LocalDateTime timeOfCreation;
    Long userId;
}
