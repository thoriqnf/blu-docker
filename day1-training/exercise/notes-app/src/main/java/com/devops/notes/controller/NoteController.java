package com.devops.notes.controller;

import com.devops.notes.model.Note;
import com.devops.notes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
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
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/api/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/notes")
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(note));
    }

    @PutMapping("/api/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody Note updatedNote) {
        return noteService.getNoteById(id).map(existing -> {
            existing.setTitle(updatedNote.getTitle());
            existing.setContent(updatedNote.getContent());
            return ResponseEntity.ok(noteService.updateNote(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        return noteService.getNoteById(id).map(existing -> {
            noteService.deleteNote(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
