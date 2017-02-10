package com.fikky.controllers;

import com.fikky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/admin/user/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAll());
        return "admin/user/list";
    }

    @RequestMapping("/admin/user/show/{id}")
    public String showUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "admin/user/show";
    }




       /* @RequestMapping("/story/show/{id}")
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
*/

}
