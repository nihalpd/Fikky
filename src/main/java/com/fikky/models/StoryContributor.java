package com.fikky.models;


import com.fikky.models.contributor.ContributorType;

import javax.persistence.*;

@Entity
public class StoryContributor extends AbstractDomainClass {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STORY_ID", nullable = false)
    private Story story;

    @Enumerated(EnumType.STRING)
    private ContributorType type;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public ContributorType getType() {
        return type;
    }

    public void setType(ContributorType type) {
        this.type = type;
    }
}
