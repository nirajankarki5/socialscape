package com.nirajan.socialscape.socialscape.service.event;

import com.nirajan.socialscape.socialscape.dao.EventRepository;
import com.nirajan.socialscape.socialscape.dto.EventRequest;
import com.nirajan.socialscape.socialscape.entity.Category;
import com.nirajan.socialscape.socialscape.entity.Event;
import com.nirajan.socialscape.socialscape.entity.Tag;
import com.nirajan.socialscape.socialscape.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    // define field for EventRepository
    private EventRepository eventRepository;
    private TagService tagService;

    // for constructor injection
    @Autowired
    public EventServiceImpl(EventRepository eventRepository,TagService tagService) {
        this.eventRepository = eventRepository;
        this.tagService = tagService;
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
    public Event save(EventRequest request) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setLocation(request.getLocation());
        event.setCategory(Category.valueOf(request.getCategory()));
        event.setImage(request.getImage());

        // ✅ Delegate to tagService to get or create tags
        List<Tag> tags = tagService.getOrCreateTags(request.getTags());
        event.setTags(tags);

        return eventRepository.save(event);
    }

    @Override
    public void delete(int id) {
        eventRepository.deleteById(id);
    }
}
