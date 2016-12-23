package com.fikky.controllers;

import com.fikky.models.Chapter;
import com.fikky.models.Story;
import com.fikky.service.ChapterService;
import com.fikky.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class ChapterController {

    private StoryService storyService;
    private ChapterService chapterService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService= storyService;
    }


    @RequestMapping("/chapter/show/{id}")
    public String chapter(@PathVariable("id") Integer id, Model model) {
        Chapter chapter = chapterService.getById(id);
        model.addAttribute("chapter", chapter);
        return "chapter/show";
    }

    @RequestMapping("/chapter/list/{id}")
    public String chapters(@PathVariable("id") Integer storyId, Model model) {
        Story story = storyService.getById(storyId);
        Set<Chapter> chapters = story.getChapters();
        model.addAttribute("story", story);
        model.addAttribute("chapters", chapters);
        return "chapter/list";
    }

}