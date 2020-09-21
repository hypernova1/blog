package org.blog.api.repository;

import org.blog.api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by melchor
 * Date: 2020/09/21
 * Time: 9:02 PM
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);
}
