package com.fikky.service.repo;

import com.fikky.configuration.auth.AuthenticationFacade;
import com.fikky.models.Story;
import com.fikky.models.StoryContributor;
import com.fikky.models.User;
import com.fikky.models.contributor.ContributorType;
import com.fikky.repositories.StoryRepository;
import com.fikky.service.StoryContributorService;
import com.fikky.service.StoryService;
import com.fikky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("repo")
public class StoryServiceRepoImpl implements StoryService {

    private StoryRepository storyRepository;
    private StoryContributorService storyContributorService;
    private UserService userService;
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Autowired
    public void setStoryContributorService(StoryContributorService storyContributorService) {
        this.storyContributorService = storyContributorService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthenticationFacade(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public List<?> listAll() {
        List<Story> stories = new ArrayList<>();
        storyRepository.findAll().forEach(stories::add);
        return stories;
    }

    @Override
    public Story getById(Integer id) {
        return storyRepository.findOne(id);
    }

    @Override
    public Story saveOrUpdate(Story domainObject) {
        Story savedStory = storyRepository.save(domainObject);
        //Add original story creator as the owner.
        if (domainObject.getId() == null) {
            saveOwnerContributor(savedStory);
        }
        return savedStory;
    }

    @Override
    public void delete(Integer id) {
        storyRepository.delete(id);
    }

    private void saveOwnerContributor(Story currentStory) {
        Authentication authentication = authenticationFacade.getAuthentication();
        String username = authentication.getName();

        User currentUser = userService.findByUsername(username);

        StoryContributor storyContributor = new StoryContributor();
        storyContributor.setUser(currentUser);
        storyContributor.setStory(currentStory);
        storyContributor.setType(ContributorType.OWNER);

        storyContributorService.saveOrUpdate(storyContributor);

    }
}
