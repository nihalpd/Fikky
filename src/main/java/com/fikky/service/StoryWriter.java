package com.fikky.service;

import org.springframework.stereotype.Service;

/**
 * Created by derekmiranda on 10/20/16.
 */
@Service
public class StoryWriter implements StoryWriterService {

    @Override
    public void editStory(int storyID) {
        // TODO: connect to Story-writing view
        // take submitted text and put into repo

        // autosave?
    }

    @Override
    public void createStory() {
        // TODO: create new story/repo? thru GHClient
    }

}
