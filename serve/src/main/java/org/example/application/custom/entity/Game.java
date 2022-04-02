package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Game extends BaseEntity {
    @Column(columnDefinition = "varchar(64) default null ")
    private String name;
    @Column(columnDefinition = "varchar(64) default null ")
    private String introduction;
    @Column(columnDefinition = "int default null ")
    private int score;
    @Column(columnDefinition = "int default 0 ")
    private int scoreCount;
    @OneToMany
    private List<GameComment> gameComments;
    @OneToOne
    private FileInfo image;
}
