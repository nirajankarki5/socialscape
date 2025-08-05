package com.nirajan.socialscape.socialscape.dao;

import com.nirajan.socialscape.socialscape.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
    // "save", "saveAll", "findById", "findAll", "findAllById", "deleteById", "delete", "deleteAll", "existsById", "count"
}
