package com.devops.notes.service;

import com.devops.notes.model.Note;
import com.devops.notes.repository.NoteRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Cacheable(value = "notesList")
    public List<Note> getAllNotes() {
        return noteRepository.findAllByOrderByUpdatedAtDesc();
    }

    @Cacheable(value = "note", key = "#id")
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @CacheEvict(value = "notesList", allEntries = true)
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @CacheEvict(value = "notesList", allEntries = true)
    @CachePut(value = "note", key = "#result.id")
    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }

    @CacheEvict(value = {"notesList", "note"}, allEntries = true)
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
