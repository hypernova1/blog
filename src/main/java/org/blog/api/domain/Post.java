package org.blog.api.domain;

import lombok.*;
import org.blog.api.config.security.UserPrincipal;
import org.blog.api.domain.audit.DateAudit;
import org.blog.api.exception.NotEqualWriterException;
import org.blog.api.web.payload.PostDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    private Category category;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "is_active")
    private final Boolean active = true;

    @Builder
    protected Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void update(PostDto.UpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void setWriter(Account writer) {
        this.writer = writer;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
