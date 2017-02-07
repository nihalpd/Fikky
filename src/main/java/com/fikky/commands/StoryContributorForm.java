package com.fikky.commands;

import com.fikky.models.contributor.ContributorType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class StoryContributorForm {

    private Integer id;

    private Integer version;

    @NotEmpty
    private String username;

    @NotNull
    private Integer storyId;


    private ContributorType type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public ContributorType getType() {
        return type;
    }

    public void setType(ContributorType type) {
        this.type = type;
    }
}
