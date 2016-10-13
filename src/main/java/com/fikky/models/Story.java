package com.fikky.models;


import java.util.Set;

public class Story {

    private final long id;
    private String contents;
    private Set<Editor> editors;
    private Owner owner;

    public Story(long id) {
    this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }
}
