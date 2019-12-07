package com.lepczynski.hubert.organizer.Models;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "task", schema = "organizer")
public class Task
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Size(max = 1000)
    @NotNull
    private String content;

    @Min(0) @Max(9)
    @NotNull
    private int importance;

    @Min(0) @Max(9)
    @NotNull
    private int urgency;

    @NotNull
    private boolean isDone = false;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime timeOfCreation;

    @Nullable
    @Enumerated
    @Column(columnDefinition = "smallint")//or create enumeration entity?
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public Task(String content, int importance, int urgency, Category category, Task masterTask)
    {
        this(content);
        this.importance = importance;
        this.urgency = urgency;
        this.category = category;

    }

    public Task(String content, int importance, int urgency, Category category)
    {
        this(content);
        this.importance = importance;
        this.urgency = urgency;
        this.category = category;

    }
    public Task(String content)
    {
        this.setContent(content);
        timeOfCreation = LocalDateTime.now();
    }

    public Task()
    {
        timeOfCreation = LocalDateTime.now();
        this.setImportance(0);
        this.setUrgency(0);
    }



}
