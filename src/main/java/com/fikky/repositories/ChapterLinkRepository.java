package com.fikky.repositories;

import com.fikky.models.ChapterLink;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChapterLinkRepository extends CrudRepository<ChapterLink, Integer> {
    List<ChapterLink> findByChildId(Integer childId);
    List<ChapterLink> findByParentId(Integer parentId);
}
