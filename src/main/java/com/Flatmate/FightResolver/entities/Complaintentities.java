package com.Flatmate.FightResolver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Complaintentities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String type; // Noise, Cleanliness, Bills, Pets, etc.
    private String severity; // Minor, Annoying, Major, Nuclear

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Userentities filedBy;

    @ManyToOne
    @JoinColumn(name = "flat_id", nullable = false)
    private Flatentities flat;

    private int upvotes = 0;
    private int downvotes = 0;
    private boolean resolved = false;

    private LocalDateTime timestamp=LocalDateTime.now();

    public Complaintentities(String description, int downvotes, Userentities filedBy, Flatentities flat, Long id, boolean resolved, String severity, LocalDateTime timestamp, String title, String type, int upvotes) {
        this.description = description;
        this.downvotes = downvotes;
        this.filedBy = filedBy;
        this.flat = flat;
        this.id = id;
        this.resolved = resolved;
        this.severity = severity;
        this.timestamp = timestamp;
        this.title = title;
        this.type = type;
        this.upvotes = upvotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public Userentities getFiledBy() {
        return filedBy;
    }

    public void setFiledBy(Userentities filedBy) {
        this.filedBy = filedBy;
    }

    public Flatentities getFlat() {
        return flat;
    }

    public void setFlat(Flatentities flat) {
        this.flat = flat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}
