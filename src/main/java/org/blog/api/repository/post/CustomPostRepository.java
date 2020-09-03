package org.blog.api.repository.post;

import org.blog.api.domain.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 11:50 AM
 */
public interface CustomPostRepository {
    List<Post> findByKeyword(Pageable pageable, String keyword);
}
