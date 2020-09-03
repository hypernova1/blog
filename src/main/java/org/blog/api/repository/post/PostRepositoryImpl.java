package org.blog.api.repository.post;

import com.querydsl.jpa.JPQLQuery;
import org.blog.api.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.blog.api.domain.QPost.post;

/**
 * Created by melchor
 * Date: 2020/09/03
 * Time: 11:39 AM
 */

@Repository
public class PostRepositoryImpl extends QuerydslRepositorySupport implements CustomPostRepository {

    public PostRepositoryImpl() {
        super(Post.class);
    }

    public List<Post> findByKeyword(Pageable pageable, String keyword) {
        JPQLQuery<Post> query = from(post);
        if (!StringUtils.isEmpty(keyword)) {
            query.where(post.title.contains(keyword), post.content.contains(keyword));
        };
        return query.where(post.active.eq(true)).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }
}
