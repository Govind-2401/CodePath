package com.codepath.backend.service;

import com.codepath.backend.dto.NoteRequest;
import com.codepath.backend.dto.NoteResponse;

import java.util.List;

public interface NoteService {

    NoteResponse createNote(NoteRequest request);

    List<NoteResponse> getAllNotes();

    NoteResponse getNoteById(Long id);

    List<NoteResponse> getNotesByTopic(Long topicId);

    NoteResponse updateNote(Long id, NoteRequest request);

    void deleteNote(Long id);
}