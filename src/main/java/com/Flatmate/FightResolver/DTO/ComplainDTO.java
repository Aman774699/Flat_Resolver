package com.Flatmate.FightResolver.DTO;

import com.Flatmate.FightResolver.entities.Flatentities;
import com.Flatmate.FightResolver.entities.Userentities;

import java.time.LocalDateTime;

public class ComplainDTO {
    private Long id;
    private String title;
    private String description;
    private String type;
    private String severity;
    private String filedBy;
    private String flatCode;
    private int upvotes;
    private int downvotes;
    private boolean resolved;
    private LocalDateTime timestamp;

    public ComplainDTO(Long id, String title, String description, String type, String severity,
                       String filedBy, String flatCode, int upvotes, int downvotes,
                       boolean resolved, LocalDateTime timestamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.severity = severity;
        this.filedBy = filedBy;
        this.flatCode = flatCode;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.resolved = resolved;
        this.timestamp = timestamp;
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

    public String getFiledBy() {
        return filedBy;
    }

    public void setFiledBy(String filedBy) {
        this.filedBy = filedBy;
    }

    public String getFlatCode() {
        return flatCode;
    }

    public void setFlatCode(String flatCode) {
        this.flatCode = flatCode;
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
