package org.sam.blog.doamin;

import lombok.Getter;
import org.sam.blog.doamin.audit.DateAudit;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
public class Comment extends DateAudit {

    private String content;
    @ManyToOne
    private Member writer;

    @OneToMany
    private List<Comment> subComments;

}
