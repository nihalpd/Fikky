package com.fikky.converters;

import com.fikky.commands.StoryContributorForm;
import com.fikky.models.Story;
import com.fikky.models.StoryContributor;
import com.fikky.models.User;
import com.fikky.service.StoryContributorService;
import com.fikky.service.StoryService;
import com.fikky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StoryContributorFormToStoryContributor implements Converter<StoryContributorForm, StoryContributor> {

    private StoryContributorService storyContributorService;
    private StoryService storyService;
    private UserService userService;

    @Autowired
    public void setStoryContributorService(StoryContributorService storyContributorService) {
        this.storyContributorService = storyContributorService;
    }

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public StoryContributor convert(StoryContributorForm storyContributorForm) {
        StoryContributor storyContributor = (storyContributorForm.getId() != null) ?
                storyContributorService.getById(storyContributorForm.getId()) : new StoryContributor();

        User user = userService.findByUsername(storyContributorForm.getUsername());
        Story story = storyService.getById(storyContributorForm.getStoryId());

        storyContributor.setUser(user);
        storyContributor.setStory(story);
        storyContributor.setType(storyContributorForm.getType());

        return storyContributor;
    }
}
