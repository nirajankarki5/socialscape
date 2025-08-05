package com.nirajan.socialscape.socialscape.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Convert(converter = CategoryConverter.class)
    @Column(name = "category")
    private Category category;

    @Column(name = "image")
    private String image;

    // adding field for Bi-directional OneToMany with Booking
    @OneToMany(mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Booking> bookings;

    // Event is the OWNING SIDE
    // Tag is the inverse/non-owning side
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "event_tags", joinColumns = @JoinColumn(name = "event_id"),
                                    inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private List<Tag> tags;

    // constructors
    public Event() {}

    public Event(String title, String description, LocalDateTime startTime, LocalDateTime endTime, String location, Status status, Category category, String image) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.status = status;
//        this.createdAt = createdAt;
        this.category = category;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    // many to many relationship
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    // adding convenience methods for bi-directional relationship
    public void addBooking(Booking booking) {
        if(bookings == null) {
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
        booking.setEvent(this);
    }

    // adding convenience methids for tags
    public void addTag(Tag tag) {
        if(tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }

    // Implement equals and hashCode methods in Booking (preferably based on immutable unique fields or id after persisted) to
    // avoid issues when dealing with collections.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return id != 0 && id == booking.getId(); // only compare if id is assigned
    }

    @Override
    public int hashCode() {
        return id != 0 ? Integer.hashCode(id) : super.hashCode();
    }

    /*
    - equals returns true only if the two objects are of the same class and have the same non-zero id.
    - If the id is zero (meaning the entity is transient/not yet persisted), equals returns false unless it is the exact same instance (this == o).
    - The hashCode uses the id if available; otherwise falls back on Object.hashCode() (which is usually OK for transient entities).
    - This implementation assumes the entity’s identity is its database-generated ID.
     */
}
