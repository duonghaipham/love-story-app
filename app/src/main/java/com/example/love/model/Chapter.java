package com.example.love.model;

public class Chapter {
    private String story;
    private String chapter;
    private String content;

    public Chapter(String story, String chapter, String content) {
        this.story = story;
        this.chapter = chapter;
        this.content = content;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
