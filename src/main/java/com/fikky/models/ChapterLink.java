package com.fikky.models;

import javax.persistence.*;

@Entity
public class ChapterLink implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private final Integer parentId;

    @Column(nullable = false)
    private final Integer childId;

    @Column
    private String name;

    public ChapterLink(Integer parentId, Integer childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getChildId() {
        return childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
