package com.fikky.models;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Story implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STORY_ID", unique = true)
    private Integer id;

    @Version
    private Integer version;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "story")
    private Set<Chapter> chapters;

    private String description;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

}
