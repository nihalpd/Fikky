package com.fikky.service.repo;

import com.fikky.models.Story;
import com.fikky.models.StoryContributor;
import com.fikky.models.User;
import com.fikky.repositories.StoryContributorRepository;
import com.fikky.service.StoryContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryContributorServiceRepoImpl implements StoryContributorService {

    private StoryContributorRepository storyContributorRepository;

    @Autowired
    public void setStoryContributorRepository(StoryContributorRepository storyContributorRepository) {
        this.storyContributorRepository = storyContributorRepository;
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
    public List<StoryContributor> findByStory(Story story) {
        return storyContributorRepository.findByStory(story);
    }

    @Override
    public List<StoryContributor> findByUser(User user) {
        return storyContributorRepository.findByUser(user);
    }
}
