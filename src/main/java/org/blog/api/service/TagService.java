package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Tag;
import org.blog.api.repository.TagRepository;
import org.blog.api.web.payload.TagDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by melchor
 * Date: 2020/09/19
 * Time: 12:09 AM
 */
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tags;
    private final ModelMapper modelMapper;

    public Tag findOrCreate(String name) {
        return tags.findByName(name).orElseGet(() -> tags.save(Tag.builder().name(name).build()));
    }
    public void findOrCreate(Collection<TagDto> tagList) {
        Set<String> unregisterTagNames = new HashSet<>();
        tagList.forEach(tag -> {
            if (!this.tags.findByName(tag.getName()).isPresent()) {
                unregisterTagNames.add(tag.getName());
            }
        });
        Set<Tag> unregisterTags = new HashSet<>();
        unregisterTagNames.forEach(tagName -> {
            unregisterTags.add(Tag.builder().name(tagName).build());
        });
        tags.saveAll(unregisterTags);
    }

    public List<TagDto> findTagList(String name) {
        List<Tag> tagList = tags.findAllByNameLike(name);

        return tagList.stream().map(tag -> modelMapper.map(tag, TagDto.class)).collect(Collectors.toList());
    }

}
