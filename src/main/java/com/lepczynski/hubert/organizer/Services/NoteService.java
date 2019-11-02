package com.lepczynski.hubert.organizer.Services;

import com.lepczynski.hubert.organizer.Controllers.ExceptionHandling.EntityNotFoundException;
import com.lepczynski.hubert.organizer.Controllers.ExceptionHandling.InvalidParametersForEntityCreationException;
import com.lepczynski.hubert.organizer.Models.DTOs.NoteDTO;
import com.lepczynski.hubert.organizer.Models.Note;
import com.lepczynski.hubert.organizer.Repositories.NoteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("noteService")
public class NoteService
{
    private final NoteRepository repository;
    private final ModelMapper modelMapper;
    @Autowired
    NoteService(NoteRepository noteRepository, ModelMapper modelMapper)
    {
        repository = noteRepository;
        this.modelMapper = modelMapper;
        configureModelMapperForMappingNotes();
    }
    public NoteDTO getNoteById(Long id)
    {
        Note note = repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(id, "Note"));
        NoteDTO noteDTO = modelMapper.map(note, NoteDTO.class);
        return noteDTO;
    }

    public List<NoteDTO> getNotesForUserByUserId(Long userId)
    {
        ArrayList<Note> notes = new ArrayList<>(repository.findByUserId(userId));
        ArrayList<NoteDTO> notesDTOs = new ArrayList<>();
        notes.forEach(note -> notesDTOs.add(modelMapper.map(note,NoteDTO.class)));
        return notesDTOs;
    }
    public List<NoteDTO> getAllNotes()
    {
        ArrayList<Note> notes = repository.findAll();
        ArrayList<NoteDTO> notesDTOs = new ArrayList<>();
        notes.forEach(note -> notesDTOs.add(modelMapper.map(note,NoteDTO.class)));
        return notesDTOs;
    }

    public void save(NoteDTO note)
    {
        if(note.getUserId()!=null)
        {
            Note newNote = createNoteFromNoteDTO(note);
            repository.save(newNote);
        }else
            throw new InvalidParametersForEntityCreationException("Note");

    }

    public boolean update(NoteDTO note)
    {
        if(!repository.existsById(note.getId()))
            return false;
        {
            Note newNote = createNoteFromNoteDTO(note);
            repository.save(newNote);
            return true;
        }
    }
    private void configureModelMapperForMappingNotes()
    {
        PropertyMap<Note, NoteDTO> noteMap = new PropertyMap<Note, NoteDTO>() {
            protected void configure() {
                map().setUserId(source.getUser().getId());

            }
        };
        modelMapper.addMappings(noteMap);
    }
    private Note createNoteFromNoteDTO(NoteDTO noteDTO)
    {
        return new Note(noteDTO.getTitle(), noteDTO.getContent(), noteDTO.getImportance());
    }

}
