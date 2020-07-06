package org.sam.blog.doamin;

import lombok.Getter;
import org.sam.blog.doamin.audit.DateAudit;

import javax.persistence.Entity;

@Entity
@Getter
public class Category extends DateAudit {

    private String name;
    private String path;

}
