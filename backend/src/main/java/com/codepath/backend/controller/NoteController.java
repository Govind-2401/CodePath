package com.codepath.backend.controller;

import com.codepath.backend.dto.NoteRequest;
import com.codepath.backend.dto.NoteResponse;
import com.codepath.backend.service.NoteService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            @Valid @RequestBody NoteRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteService.createNote(request));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes() {

        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(
            @PathVariable Long id) {

        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<NoteResponse>> getNotesByTopic(
            @PathVariable Long topicId) {

        return ResponseEntity.ok(noteService.getNotesByTopic(topicId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request) {

        return ResponseEntity.ok(noteService.updateNote(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable Long id) {

        noteService.deleteNote(id);

        return ResponseEntity.noContent().build();
    }
}