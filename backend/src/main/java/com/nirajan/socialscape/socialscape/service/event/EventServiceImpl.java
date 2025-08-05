package com.nirajan.socialscape.socialscape.service.event;

import com.nirajan.socialscape.socialscape.dao.EventRepository;
import com.nirajan.socialscape.socialscape.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    // define field for EventRepository
    private EventRepository eventRepository;

    // for constructor injection
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(int id) {
        Optional<Event> result = eventRepository.findById(id);

        Event event = null;
        if(result.isPresent()){
            event = result.get();
        } else {
            throw new RuntimeException("Event not found with that id");
        }
        return event;
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void delete(int id) {
        eventRepository.deleteById(id);
    }
}
