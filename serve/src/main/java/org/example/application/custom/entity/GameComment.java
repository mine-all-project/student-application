package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@ToString
public class GameComment extends BaseEntity {
    public GameComment() {
    }

    public GameComment(String content) {
        this.content = content;
    }

    @Column(columnDefinition = "varchar(64) default null ")
    private String content;
}
