package com.fikky.validators;

import com.fikky.commands.StoryContributorForm;
import com.fikky.models.Story;
import com.fikky.models.User;
import com.fikky.service.StoryContributorService;
import com.fikky.service.StoryService;
import com.fikky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StoryContributorFormValidator implements Validator {

    private StoryContributorService storyContributorService;
    private UserService userService;
    private StoryService storyService;

    @Autowired
    public void setStoryContributorService(StoryContributorService storyContributorService) {
        this.storyContributorService = storyContributorService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return StoryContributorForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        StoryContributorForm storyContributorForm = (StoryContributorForm) o;
        String username = storyContributorForm.getUsername();
        User user = userService.findByUsername(username);

        if (user == null) {
            errors.rejectValue("username","UsernameNotFound.userCreateForm.username",
                                "User with username \""+ username + "\" does not exist.");
            return;
        }

        Story story = storyService.getById(storyContributorForm.getStoryId());
        if (storyContributorService.findContributorsByStory(story).contains(user)) {
            errors.rejectValue("username", "UsernameExists.userCreateForm.username",
                                username + " is already a contributor.");
        }
    }
}
