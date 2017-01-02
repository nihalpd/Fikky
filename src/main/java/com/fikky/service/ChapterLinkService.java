package com.fikky.service;

import com.fikky.models.Chapter;
import com.fikky.models.ChapterLink;

import java.util.List;

public interface ChapterLinkService {

    List<ChapterLink> listAll();
    List<ChapterLink> getChildLinks(Integer parentId);
    List<ChapterLink> getParentLinks(Integer childId);
    ChapterLink addLink(Chapter parent, Chapter child);
    void delete(Integer id);

}
