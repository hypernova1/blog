package org.blog.api.repository;

import org.blog.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:31 PM
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
