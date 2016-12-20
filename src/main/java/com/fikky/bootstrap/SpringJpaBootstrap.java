package com.fikky.bootstrap;

import com.fikky.models.Chapter;
import com.fikky.models.Story;
import com.fikky.service.ChapterService;
import com.fikky.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*As of right now this class is for loading in data for dev purposes.*/

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private StoryService storyService;
    private ChapterService chapterService;

    @Autowired
    private void setProductService(StoryService storyService) {
        this.storyService = storyService;
    }

    @Autowired
    private void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Story testStory = loadStories();
        loadChapters(testStory);
    }

    private void loadChapters(Story story) {
        Chapter ch1 = new Chapter();
        ch1.setContents("This is a story all about how");

        Chapter ch2 = new Chapter();
        ch2.setContents("my life got twist turned upside-down");

        ch1.setStory(story);
        ch2.setStory(story);

        chapterService.saveOrUpdate(ch1);
        chapterService.saveOrUpdate(ch2);

    }

    private Story loadStories() {
        Story story1 = new Story();
        story1.setName("Fresh Prince");
        story1.setDescription("adsfdsafsadfkisadf");

        Story savedStory =  storyService.saveOrUpdate(story1);
        return savedStory;
    }


}
