package com.lepczynski.hubert.organizer.Controllers;

import com.lepczynski.hubert.organizer.Models.DTOs.NoteDTO;
import com.lepczynski.hubert.organizer.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController
{
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService)
    {
        this.noteService = noteService;
    }

    @GetMapping("/notes/{id}")
    public NoteDTO getNoteById(@PathVariable Long id)
    {
        //TODO check if current user has right to this - how to authentication?
        return noteService.getNoteById(id);
    }


    @GetMapping("/notes")
    public List <NoteDTO> getAllNotes()
    {
        return noteService.getAllNotes();
    }


    @GetMapping("/notesForUser")
    public List <NoteDTO> getAllNotesForUserById(@PathVariable Long userId)
    {
        return noteService.getNotesForUserByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/notesForUser")
    public void updateNote(@RequestBody NoteDTO note)
    {
        noteService.update(note);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/notesForUser")
    public void newNote(@RequestBody NoteDTO note)
    {
        noteService.save(note);
    }
}
