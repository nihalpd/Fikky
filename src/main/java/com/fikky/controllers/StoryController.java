package com.fikky.controllers;

import com.fikky.service.StoryReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoryController {


    private StoryReaderService storyReaderService;

    @Autowired
    public void setStoryReaderService(StoryReaderService storyReaderService) {
        this.storyReaderService = storyReaderService;
    }

    @RequestMapping("/stories")
    public String stories(String name, Model model) {
        model.addAttribute("stories", storyReaderService.getUserStories());
        return "stories";
    }

    @RequestMapping("/story/{id}")
    public String story(@PathVariable("id") int id, Model model) {
        model.addAttribute("story", storyReaderService.getStorybyID(id));
        return "story";
    }

    @RequestMapping("/story/new")
    public String newStory(Model model) {
        return "storyform";
    }

}
