package org.blog.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class Post extends DateAudit {

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Member writer;

    @Column(name = "is_active")
    private final Boolean active = true;

    public void update(PostDto.UpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

}
