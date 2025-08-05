package com.nirajan.socialscape.socialscape.dao;

import com.nirajan.socialscape.socialscape.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    // "save", "saveAll", "findById", "findAll", "findAllById", "deleteById", "delete", "deleteAll", "existsById", "count"

    // name field matches any value within a given collection (like a list or set) of names
    List<Tag> findByNameIn(List<String> names);
    Tag findByName(String name);
}
