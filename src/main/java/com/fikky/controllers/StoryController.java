package com.fikky.controllers;

import com.fikky.models.Story;
import com.fikky.service.StoryContributorService;
import com.fikky.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoryController {

    private StoryService storyService;
    private StoryContributorService storyContributorService;

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @Autowired
    public void setStoryContributorService(StoryContributorService storyContributorService) {
        this.storyContributorService = storyContributorService;
    }

    @RequestMapping("/story/list")
    public String stories(Model model) {
        model.addAttribute("stories", storyContributorService.findCurrentUserStories());
        return "story/list";
    }

    @RequestMapping("/story/show/{id}")
    public String story(@PathVariable("id") Integer id, Model model) {
        Story story = storyService.getById(id);
        model.addAttribute("story", story);
        return "story/show";
    }

    @RequestMapping("/story/new")
    public String addStory(Model model) {
        Story story = new Story();
        model.addAttribute("story", story);
        return "story/storyform";
    }

    @RequestMapping("/story/edit/{id}")
    public String updateStory(@PathVariable("id") Integer id, Model model) {
        Story story = storyService.getById(id);
        model.addAttribute("story", story);
        return "story/storyform";
    }

    @RequestMapping(value = "/story", method = RequestMethod.POST)
    public String newStory(Story story) {
        Story savedStory = storyService.saveOrUpdate(story);
        return "redirect:/story/show/" + savedStory.getId();
    }

    @RequestMapping("/story/delete/{id}")
    public String deleteStory(@PathVariable("id") Integer id, Model model) {
        storyService.delete(id);
        return "redirect:/story/list";
    }

}
