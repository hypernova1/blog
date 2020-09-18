package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.domain.Tag;
import org.blog.api.repository.TagRepository;
import org.springframework.stereotype.Service;

/**
 * Created by melchor
 * Date: 2020/09/19
 * Time: 12:09 AM
 */
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tags;

    public Tag findOrCreate(String name) {
        return tags.findByName(name).orElseGet(() -> tags.save(Tag.builder().name(name).build()));
    }

}
