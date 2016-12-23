package com.fikky.bootstrap;

import com.fikky.models.Chapter;
import com.fikky.models.Story;
import com.fikky.service.ChapterService;
import com.fikky.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

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
        ch1.setContents("Now this is the story all about how<br>" +
                        "My life got flipped, turned upside down<br>" +
                        "And I'd like to take a minute just sit right there<br>" +
                        "I'll tell you how I became the prince of a town called Bel Air");
        ch1.setName("This is a story all about how");

        Chapter ch2 = new Chapter();
        ch2.setName("In West Philadelphia born and raised");
        ch2.setContents("In west Philadelphia born and raised<br>" +
                "On the playground where I spent most of my days<br>" +
                "Chilling out, maxing, relaxing all cool<br>" +
                "And all shooting some b-ball outside of the school<br>" +
                "When a couple of guys who were up to no good<br>" +
                "Started making trouble in my neighborhood<br>" +
                "I got in one little fight and my mom got scared<br>" +
                "And said you're moving with your auntie and uncle in Bel Air");


        Chapter ch3 = new Chapter();
        ch3.setName("I begged and pleaded with her the other day");
        ch3.setContents("I begged and pleaded with her the other day<br>" +
                "But she packed my suitcase and sent me on my way<br>" +
                "She gave me a kissin' and she gave me my ticket<br>" +
                "I put my Walkman on and said I might as well kick it");


        Chapter ch4 = new Chapter();
        ch4.setName("First class, yo this is bad");
        ch4.setContents("First class, yo this is bad,<br>" +
                "Drinking orange juice out of a champagne glass<br>" +
                "Is this what the people of Bel Air livin' like<br>" +
                "Hm this might be alright!<br>");


        Chapter ch5 = new Chapter();
        ch5.setName("I whistled for a cab and when it came near");
        ch5.setContents("I whistled for a cab and when it came near<br>" +
                "the license plate said 'Fresh' and had dice in the mirror<br>" +
                "If anything I could say that this cab was rare<br>" +
                "But I thought now forget it, yo home to Bel Air");

        Chapter ch6 = new Chapter();
        ch6.setName("I pulled up to a house about seven or eight");
        ch6.setContents("I pulled up to a house about seven or eight<br>" +
                "And I yelled to the cabbie, yo Holmes smell ya later<br>" +
                "Looked at my kingdom I was finally there<br>" +
                "To sit on my throne as the prince of Bel Air");

        ch1.setStory(story);
        ch2.setStory(story);
        ch3.setStory(story);
        ch4.setStory(story);
        ch5.setStory(story);
        ch6.setStory(story);

        ch1.setNumber(1);
        ch2.setNumber(2);
        ch3.setNumber(3);
        ch4.setNumber(4);
        ch5.setNumber(5);
        ch6.setNumber(6);

        chapterService.saveOrUpdate(ch1);
        chapterService.saveOrUpdate(ch2);
        chapterService.saveOrUpdate(ch3);
        chapterService.saveOrUpdate(ch4);
        chapterService.saveOrUpdate(ch5);
        chapterService.saveOrUpdate(ch6);

    }

    private Story loadStories() {
        Story story1 = new Story();
        story1.setName("Fresh Prince");
        story1.setDescription("adsfdsafsadfkisadf");

        Story savedStory =  storyService.saveOrUpdate(story1);

        return savedStory;
    }


}
