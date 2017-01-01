package com.fikky.converters;
import com.fikky.commands.ChapterForm;
import com.fikky.models.Chapter;
import com.fikky.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ChapterFormToChapter implements  Converter<ChapterForm, Chapter> {

    private StoryService storyService;

    @Autowired
    public void setStoryService(StoryService storyService){
        this.storyService = storyService;
    }

    @Override
    public Chapter convert(ChapterForm chapterForm) {

        Chapter chapter = new Chapter();
        chapter.setId(chapterForm.getChapterId());
        chapter.setVersion(chapterForm.getChapterVersion());
        chapter.setName(chapterForm.getName());
        chapter.setContents(chapterForm.getContents());
        chapter.setStory(storyService.getById(chapterForm.getStoryId()));

        return chapter;
    }
}
