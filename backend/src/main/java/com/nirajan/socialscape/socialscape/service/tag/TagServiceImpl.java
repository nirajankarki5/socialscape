package com.nirajan.socialscape.socialscape.service.tag;

import com.nirajan.socialscape.socialscape.dao.TagRepository;
import com.nirajan.socialscape.socialscape.entity.Event;
import com.nirajan.socialscape.socialscape.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService{
    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(int id) {
        Optional<Tag> result = tagRepository.findById(id);

        Tag tag = null;
        if(result.isPresent()){
            tag = result.get();
        } else {
            throw new RuntimeException("Event not found with that id");
        }
        return tag;
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(int id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

    // Get tags that already exist in the DB
    public List<Tag> getTagsByNames(List<String> tagNames) {
        return tagRepository.findByNameIn(tagNames);
    }

    // Create new tags (assume names don't exist yet)
    public List<Tag> createTagsByNames(List<String> tagNames) {
        List<Tag> newTags = new ArrayList<>();
        for (String name : tagNames) {
            newTags.add(new Tag(name));
        }
        return tagRepository.saveAll(newTags);
    }

    // Combined logic: get existing + create missing tags
    public List<Tag> getOrCreateTags(List<String> tagNames) {
        List<Tag> existingTags = getTagsByNames(tagNames);
        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<String> missingTagNames = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .collect(Collectors.toList());

        List<Tag> newTags = createTagsByNames(missingTagNames);

        List<Tag> allTags = new ArrayList<>();
        allTags.addAll(existingTags);
        allTags.addAll(newTags);

        return allTags;
    }
}
