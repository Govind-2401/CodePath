package com.codepath.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "topic_prerequisites",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_topic_id")
    )
    private Set<Topic> prerequisites = new HashSet<>();

    private LocalDateTime createdAt;

    public Topic() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Topic> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Set<Topic> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}