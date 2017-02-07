package com.fikky.service;


import com.fikky.commands.StoryContributorForm;
import com.fikky.models.Story;
import com.fikky.models.StoryContributor;
import com.fikky.models.User;

import java.util.List;

public interface StoryContributorService extends CRUDService<StoryContributor>{
    List<User> findContributorsByStory(Story story);
    List<Story> findStoriesByContributor(User user);
    List<Story> findCurrentUserStories();
    List<StoryContributor> findByStory(Story story);
    StoryContributor saveOrUpdateStoryContributorForm(StoryContributorForm storyContributorForm);

}
