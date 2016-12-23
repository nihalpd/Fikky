package com.fikky.service;

import com.fikky.models.Chapter;
import com.fikky.models.ChapterLink;

import java.util.List;

public interface ChapterLinkService {

    List<ChapterLink> listAll();
    List<ChapterLink> getChildren(Integer parentId);
    List<ChapterLink> getParents(Integer childId);
    ChapterLink addLink(Chapter parent, Chapter child);
    void delete(Integer id);

}
