package com.nirajan.socialscape.socialscape.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    // mappedBy ----> tags property in Event entity
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonBackReference // is applied on the back (mappedBy) side, Bidirectional @ManyToMany relationships cause infinite recursion (events -> tags -> events -> tags ...)
    private List<Event> events;

    // constructors
    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    // add convenience method for addEvent
    // ensures consistency in bi-directional relationship
    public void addEvent(Event event) {
        if(events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
        event.addTag(this);
    }
}
