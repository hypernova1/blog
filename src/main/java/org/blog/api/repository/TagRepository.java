package org.blog.api.repository;

import org.blog.api.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by melchor
 * Date: 2020/09/19
 * Time: 12:08 AM
 */
@Transactional(readOnly = true)
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);

    List<Tag> findAllByNameLike(String name);
}
