package com.codepath.backend.service.impl;

import com.codepath.backend.dto.NoteRequest;
import com.codepath.backend.dto.NoteResponse;
import com.codepath.backend.entity.Note;
import com.codepath.backend.entity.Topic;
import com.codepath.backend.exception.ResourceNotFoundException;
import com.codepath.backend.repository.NoteRepository;
import com.codepath.backend.repository.TopicRepository;
import com.codepath.backend.service.NoteService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final TopicRepository topicRepository;

    public NoteServiceImpl(
            NoteRepository noteRepository,
            TopicRepository topicRepository) {
        this.noteRepository = noteRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public NoteResponse createNote(NoteRequest request) {

        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Topic not found with ID: " + request.getTopicId()));

        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setTopic(topic);

        Note savedNote = noteRepository.save(note);

        return mapToResponse(savedNote);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponse> getAllNotes() {

        return noteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NoteResponse getNoteById(Long id) {

        Note note = noteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Note not found with ID: " + id));

        return mapToResponse(note);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponse> getNotesByTopic(Long topicId) {

        if (!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException(
                    "Topic not found with ID: " + topicId);
        }

        return noteRepository.findByTopicId(topicId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public NoteResponse updateNote(Long id, NoteRequest request) {

        Note note = noteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Note not found with ID: " + id));

        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Topic not found with ID: " + request.getTopicId()));

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setTopic(topic);

        Note updatedNote = noteRepository.save(note);

        return mapToResponse(updatedNote);
    }

    @Override
    public void deleteNote(Long id) {

        Note note = noteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Note not found with ID: " + id));

        noteRepository.delete(note);
    }

    private NoteResponse mapToResponse(Note note) {

        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getTopic().getId(),
                note.getTopic().getName(),
                note.getCreatedAt()
        );
    }
}