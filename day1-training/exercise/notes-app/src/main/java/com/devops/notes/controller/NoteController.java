package com.devops.notes.controller;

import com.devops.notes.model.Note;
import com.devops.notes.repository.NoteRepository;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> info() {
        return ResponseEntity.ok(Map.of(
                "app", System.getenv().getOrDefault("APP_NAME", "Notes API"),
                "status", "running",
                "timestamp", LocalDateTime.now().toString(),
                "cache", "redis-backed"
        ));
    }

    @GetMapping("/api/notes")
    @Cacheable(value = "notesList")
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteRepository.findAllByOrderByUpdatedAtDesc());
    }

    @GetMapping("/api/notes/{id}")
    @Cacheable(value = "note", key = "#id")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/notes")
    @CacheEvict(value = "notesList", allEntries = true)
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteRepository.save(note));
    }

    @PutMapping("/api/notes/{id}")
    @CacheEvict(value = "notesList", allEntries = true)
    @CachePut(value = "note", key = "#id")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody Note updatedNote) {
        return noteRepository.findById(id).map(existing -> {
            existing.setTitle(updatedNote.getTitle());
            existing.setContent(updatedNote.getContent());
            return ResponseEntity.ok(noteRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/notes/{id}")
    @CacheEvict(value = {"notesList", "note"}, allEntries = true)
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        return noteRepository.findById(id).map(existing -> {
            noteRepository.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
