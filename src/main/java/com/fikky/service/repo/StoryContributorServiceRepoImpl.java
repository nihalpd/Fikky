package com.fikky.service.repo;

import com.fikky.commands.StoryContributorForm;
import com.fikky.configuration.auth.AuthenticationFacade;
import com.fikky.converters.StoryContributorFormToStoryContributor;
import com.fikky.models.Story;
import com.fikky.models.StoryContributor;
import com.fikky.models.User;
import com.fikky.repositories.StoryContributorRepository;
import com.fikky.service.StoryContributorService;
import com.fikky.service.StoryService;
import com.fikky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryContributorServiceRepoImpl implements StoryContributorService {

    private StoryContributorRepository storyContributorRepository;
    private AuthenticationFacade authenticationFacade;
    private UserService userService;
    private StoryContributorFormToStoryContributor toStoryContributor;

    @Autowired
    public void setStoryContributorRepository(StoryContributorRepository storyContributorRepository) {
        this.storyContributorRepository = storyContributorRepository;
    }

    @Autowired
    public void setAuthenticationFacade(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setToStoryContributor(StoryContributorFormToStoryContributor toStoryContributor) {
        this.toStoryContributor = toStoryContributor;
    }

    @Override
    public List<?> listAll() {
        List<StoryContributor> contributors = new ArrayList<>();
        storyContributorRepository.findAll().forEach(contributors::add);
        return contributors;
    }

    @Override
    public StoryContributor getById(Integer id) {
        return storyContributorRepository.findOne(id);
    }

    @Override
    public StoryContributor saveOrUpdate(StoryContributor domainObject) {
        return storyContributorRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        storyContributorRepository.delete(id);
    }

    @Override
    public List<User> findContributorsByStory(Story story) {
        List<User> contributors = new ArrayList<>();

        for (StoryContributor storyContributor :
                storyContributorRepository.findByStory(story)) {
            contributors.add(storyContributor.getUser());
        }

        return contributors;
    }

    @Override
    public List<Story> findStoriesByContributor(User user) {
        List<Story> stories = new ArrayList<>();

        for (StoryContributor storyContributor :
                storyContributorRepository.findByUser(user)) {
            stories.add(storyContributor.getStory());
        }

        return stories;
    }

    @Override
    public List<Story> findCurrentUserStories() {
        String username = authenticationFacade.getAuthentication().getName();
        User currentUser = userService.findByUsername(username);
        return findStoriesByContributor(currentUser);
    }

    @Override
    public List<StoryContributor> findByStory(Story story) {
        return storyContributorRepository.findByStory(story);
    }

    @Override
    public StoryContributor saveOrUpdateStoryContributorForm(StoryContributorForm storyContributorForm) {
        return saveOrUpdate(toStoryContributor.convert(storyContributorForm));
    }


}
