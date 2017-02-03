package com.fikky.service;


import com.fikky.models.Story;
import com.fikky.models.StoryContributor;
import com.fikky.models.User;

import java.util.List;

public interface StoryContributorService extends CRUDService<StoryContributor>{
    List<StoryContributor> findByStory(Story story);
    List<StoryContributor> findByUser(User user);
}
