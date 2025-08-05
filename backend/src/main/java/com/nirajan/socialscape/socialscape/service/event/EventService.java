package com.nirajan.socialscape.socialscape.service.event;

import com.nirajan.socialscape.socialscape.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> findAll();
    Event findById(int id);
    Event save(Event event);
    void delete(int id);
}
