package org.blog.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.blog.api.domain.audit.DateAudit;
import org.blog.api.web.payload.CategoryDto;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by melchor
 * Date: 2020/09/20
 * Time: 9:05 PM
 */

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends DateAudit {
    @Column(nullable = false)
    private String name;

    public void update(CategoryDto categoryDto) {
        this.name = categoryDto.getName();
    }
}
