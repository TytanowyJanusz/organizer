package com.lepczynski.hubert.organizer.Models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "note", schema = "organizer")
public class Note
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Size(max = 250)
    @Null
    private String title;

    @Size(max = 100000)
    @NotNull
    private String content;

    @Min(0) @Max(9)
    private int importance;

    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeOfCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Note(){}
    public Note(String content)
    {
        this.content = content;
        timeOfCreation =  LocalDateTime.now();
        this.setImportance(0);
    }

    public Note(String content, String title)
    {
        this.setContent(content);
        this.setTitle(title);
        timeOfCreation =  LocalDateTime.now();
        this.setImportance(0);
    }

    public Note(String content, String title, int importance)
    {
        this.setContent(content);
        this.setTitle(title);
        timeOfCreation =  LocalDateTime.now();
        this.setImportance(importance);
    }

}

