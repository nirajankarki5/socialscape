package com.nirajan.socialscape.socialscape.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "location")
    private String location;

    // columnDefinition is optional
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING'")
    private Status status = Status.PENDING;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT current_timestamp", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    // columnDefinition is optional
    @Enumerated(EnumType.STRING)
    @Column(name = "category",
            columnDefinition = "ENUM('Business', 'Food & Drink', 'Health', 'Music', 'Charity & Causes', 'Community', 'Family & Education', 'Fashion', 'Film & Media', 'Home & Lifestyle', 'Science & Tech', 'Sports & Fitness', 'Travel & Outdoor')")
    private Category category;

    // constructors
    public Event() {}

    public Event(String title, String description, LocalDateTime startTime, LocalDateTime endTime, String location, Status status, LocalDateTime createdAt, Category category) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
        this.category = category;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // No setter for createdAt since it's managed by DB

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
