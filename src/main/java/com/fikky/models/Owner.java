package com.fikky.models;

import java.util.Set;

public class Owner extends Writer {

    private Set<Story> ownedStories;
    private String name;

    public Owner(String name, Story ownedStory) {
        this.name = name;
        if (ownedStory != null) {
            this.ownedStories.add(ownedStory);
        }
    }

}
