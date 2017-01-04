package com.fikky.controllers;

import com.fikky.models.Story;
import com.fikky.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class
StoryController {
    private StoryService storyService;

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @RequestMapping("/story/list")
    public String stories(String name, Model model) {
        model.addAttribute("stories", storyService.listAll());
        return "story/list";
    }

    @RequestMapping("/story/show/{id}")
    public String story(@PathVariable("id") Integer id, Model model) {
        Story story = storyService.getById(id);
        model.addAttribute("story", story);
        return "story/show";
    }
    @RequestMapping("/story/add")
    public String addStory(Model theModel)
    {
        Story theStory=new Story();
        theModel.addAttribute("story",theStory);
        return "story/addform";
    }
    @RequestMapping("/story/add/{id}")
    public String updateStory(@PathVariable("id") Integer id, Model theModel)
    {
        Story theStory = storyService.getById(id);
        theModel.addAttribute("story",theStory);
        return "story/addform";
    }

    @RequestMapping("/story/new")
    public String newStory(@ModelAttribute("story") Story story, Model model)
    {
        storyService.saveOrUpdate(story);
        return "redirect:/story/list";
    }

    @RequestMapping("/story/delete/{id}")
    public String deleteStory(@PathVariable("id") Integer id, Model model) {
        storyService.delete(id);
        return "redirect:/story/list";
    }

}
