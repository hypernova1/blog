package org.blog.api.repository.post;

import org.blog.api.domain.Account;
import org.blog.api.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:31 PM
 */
@Transactional(readOnly = true)
public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {

    Optional<Post> findByIdAndWriter(Long id, Account writer);

    @Query("SELECT p FROM Post p WHERE p.active = true")
    @NonNull
    Page<Post> findAll(@Nullable Pageable pageable);

    @Modifying
    @Query("update Post p SET p.active = false WHERE p.id = :id")
    void deleteById(Long id);

}
