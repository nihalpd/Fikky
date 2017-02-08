package com.fikky.controllers;

import com.fikky.commands.StoryContributorForm;
import com.fikky.models.Story;
import com.fikky.models.StoryContributor;
import com.fikky.service.StoryContributorService;
import com.fikky.service.StoryService;
import com.fikky.validators.StoryContributorFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StoryContributorController {

    private StoryService storyService;
    private StoryContributorService storyContributorService;
    private StoryContributorFormValidator contributorFormValidator;

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @Autowired
    public void setStoryContributorService(StoryContributorService storyContributorService) {
        this.storyContributorService = storyContributorService;
    }

    @Autowired
    public  void setContributorFormValidator(StoryContributorFormValidator formValidator) {
        this.contributorFormValidator = formValidator;
    }

    @RequestMapping("/story/contributors/{id}")
    public String viewContributors(@PathVariable("id") Integer id, Model model) {
        Story story = storyService.getById(id);
        List<StoryContributor> contributors = storyContributorService.findByStory(story);
        model.addAttribute("contributors", contributors);
        model.addAttribute("story", story);
        if (!model.containsAttribute("storyContributorForm")) {
            StoryContributorForm storyContributorForm = new StoryContributorForm();
            storyContributorForm.setStoryId(id);
            model.addAttribute("storyContributorForm", storyContributorForm);
        }
        return "story/contributors/list";
    }

    @RequestMapping(value = "story/contributors/new", method = RequestMethod.POST)
    public String addContributor(@Valid StoryContributorForm storyContributorForm,
                                 BindingResult bindingResult, RedirectAttributes attributes) {
        contributorFormValidator.validate(storyContributorForm, bindingResult);
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.storyContributorForm", bindingResult);
            attributes.addFlashAttribute("storyContributorForm", storyContributorForm);
            return "redirect:/story/contributors/" + storyContributorForm.getStoryId();
        }
        storyContributorService.saveOrUpdateStoryContributorForm(storyContributorForm);
        return "redirect:/story/contributors/" + storyContributorForm.getStoryId();
    }

    @RequestMapping("story/contributors/{storyid}/delete/{id}")
    public String removeContributor(@PathVariable("id") Integer id, @PathVariable("storyid") Integer storyid)  {
        storyContributorService.delete(id);
        return "redirect:/story/contributors/" + storyid;
    }
}
