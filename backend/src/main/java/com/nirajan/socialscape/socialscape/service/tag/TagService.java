package com.nirajan.socialscape.socialscape.service.tag;

import com.nirajan.socialscape.socialscape.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();
    Tag findById(int id);
    Tag save(Tag tag);
    void delete(int id);
    Tag findByName(String name);
    List<Tag> getTagsByNames(List<String> tagNames);
    List<Tag> createTagsByNames(List<String> tagNames);
    List<Tag> getOrCreateTags(List<String> tagNames);
}
