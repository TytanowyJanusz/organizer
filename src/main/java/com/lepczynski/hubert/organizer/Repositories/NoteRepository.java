package com.lepczynski.hubert.organizer.Repositories;

import com.lepczynski.hubert.organizer.Models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>
{
    List<Note> findByUserId(Long userId);
    ArrayList<Note> findAll();
}
