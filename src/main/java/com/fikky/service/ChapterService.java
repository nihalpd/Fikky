package com.fikky.service;

import com.fikky.commands.ChapterForm;
import com.fikky.models.Chapter;

import java.util.List;


public interface ChapterService extends CRUDService<Chapter> {
    Chapter saveOrUpdateChapterForm(ChapterForm chapterForm);
    List<Chapter> getChildren(Integer id);
    List<Chapter> getParents(Integer id);
}
