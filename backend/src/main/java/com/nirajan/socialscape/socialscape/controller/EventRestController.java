package com.nirajan.socialscape.socialscape.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nirajan.socialscape.socialscape.dto.EventRequest;
import com.nirajan.socialscape.socialscape.entity.Event;
import com.nirajan.socialscape.socialscape.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EventRestController {

    private EventService eventService;
    private ObjectMapper objectMapper;

    @Autowired
    public EventRestController(EventService eventService, ObjectMapper objectMapper) {
        this.eventService = eventService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable int id) {
        return eventService.findById(id);
    }

    @PostMapping("/events")
    public Event addEvent(@RequestBody EventRequest request) {
        Event savedEvent = eventService.save(request);
        return savedEvent;
    }

    @PatchMapping("/events/{id}")
    public Event editEvent(@PathVariable int id, @RequestBody Map<String, Object> payLoad) {
        Event event = eventService.findById(id);

        if(event == null) {
            throw new RuntimeException("Event not found!!!");
        }

        // throw exception if payload contains id. We do not want to update id
        if(payLoad.containsKey("id")) {
            throw new RuntimeException("id not allowed in request body!!!");
        }

        Event patchedEvent = apply(payLoad, event);
        Event dbEvent = eventService.save(patchedEvent);
        return dbEvent;
    }

    private Event apply(Map<String, Object> payLoad, Event event) {

        // convert employee object to JSON object node
        ObjectNode eventNode = objectMapper.convertValue(event, ObjectNode.class);

        // convert patchPayload map to JSON object node
        ObjectNode patchNode = objectMapper.convertValue(payLoad, ObjectNode.class);

        // merge the patch
        eventNode.setAll(patchNode);

        // convert object node to Employee class
        return objectMapper.convertValue(eventNode, Event.class);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable int id) {
        Event event = eventService.findById(id);

        if(event == null) {
            throw new RuntimeException("Employee not found!!!");
        }
        eventService.delete(id);
    }
}
