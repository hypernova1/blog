package org.blog.api.domain;

import lombok.*;
import org.blog.api.domain.audit.DateAudit;
import org.blog.api.web.payload.PostDto;

import javax.persistence.*;

/**
 * Created by melchor
 * Date: 2020/08/30
 * Time: 9:46 PM
 */
@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Post extends DateAudit {

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Account writer;

    @Column(name = "is_active")
    private final Boolean active = true;

    @Builder
    protected Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(PostDto.UpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

}
