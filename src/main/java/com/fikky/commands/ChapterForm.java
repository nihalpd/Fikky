package com.fikky.commands;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by nihal on 12/31/16.
 */
public class ChapterForm {

    private Integer chapterId;
    private Integer chapterVersion;
    private Integer storyId;

    private String name;

    @NotEmpty
    private String contents;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getChapterVersion() {
        return chapterVersion;
    }

    public void setChapterVersion(Integer chapterVersion) {
        this.chapterVersion = chapterVersion;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
