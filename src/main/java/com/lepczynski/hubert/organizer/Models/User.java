package com.lepczynski.hubert.organizer.Models;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@Table(name="users", schema = "organizer") //postgresql reserves table name User
public class User
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Size(max = 20, min = 4)
    @NotNull
    private String username;


    @Size(min = 6)
    @NotNull
    private String password;//TODO :)

    @NotNull
    private String email;


    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeOfCreation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Task> Tasks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Note> Notes;


    public User(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        timeOfCreation = LocalDateTime.now();
    }

    public User(){}


}
