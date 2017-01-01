package com.fikky.controllers;

import com.fikky.commands.ChapterForm;
import com.fikky.models.Chapter;
import com.fikky.models.Story;
import com.fikky.service.ChapterLinkService;
import com.fikky.service.ChapterService;
import com.fikky.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        Story story = chapter.getStory();
        model.addAttribute("chapter", chapter);
        model.addAttribute("story", story);
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

    @RequestMapping("/chapter/new/{id}")
    public String newChapter(@PathVariable("id") Integer storyId, Model model) {
        ChapterForm newChapterForm = new ChapterForm();
        newChapterForm.setStoryId(storyId);
        model.addAttribute("chapterForm", newChapterForm);
        return "chapter/chapterform";
    }

    @RequestMapping(value = "/chapter", method = RequestMethod.POST)
    public String saveOrUpdate(ChapterForm chapterForm) {
        Chapter savedChapter = chapterService.saveOrUpdateChapterForm(chapterForm);
        return "redirect:/chapter/show/" + savedChapter.getId();

    }
    @RequestMapping("/chapter/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("chapter", chapterService.getById(id));
        return "chapter/chapterform";

    }

    @RequestMapping("/chapter/delete/{id}")
    public String delete(@PathVariable Integer id) {
        chapterService.delete(id);
        return "redirect:/chapter/list";
    }


}