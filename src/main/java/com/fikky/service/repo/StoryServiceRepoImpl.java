package com.fikky.service.repo;

import com.fikky.models.Story;
import com.fikky.repositories.StoryRepository;
import com.fikky.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("repo")
public class StoryServiceRepoImpl implements StoryService {

    private StoryRepository storyRepository;

    @Autowired
    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
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
        return storyRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        storyRepository.delete(id);
    }
}
