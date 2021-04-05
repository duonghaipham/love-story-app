package com.example.love.model;

public class Chapter {
    private int story;
    private int id;
    private String chapter;
    private String content;

    public Chapter(int story, int id, String chapter, String content) {
        this.story = story;
        this.id = id;
        this.chapter = chapter;
        this.content = content;
    }

    public int getStory() {
        return story;
    }

    public void setStory(int story) {
        this.story = story;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
