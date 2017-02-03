package com.fikky.repositories;

import com.fikky.models.Story;
import com.fikky.models.StoryContributor;
import com.fikky.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoryContributorRepository extends CrudRepository<StoryContributor, Integer> {
    List<StoryContributor> findByStory(Story story);
    List<StoryContributor> findByUser(User user);
}
