package com.fikky.repositories;

import com.fikky.models.Story;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nihal on 12/30/16.
 */
public interface StoryRepository extends CrudRepository<Story, Integer> {
}
