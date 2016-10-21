package com.fikky.service;

import com.fikky.models.Story;

import java.util.Set;

public interface StoryReaderService {
    Set<Story> getUserStories();
    Story getStorybyID(int storyID);
}
