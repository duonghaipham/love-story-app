package com.example.love.model;

public class MiniContent {
    private int story;
    private int chapter;

    public MiniContent(int story, int chapter) {
        this.story = story;
        this.chapter = chapter;
    }

    public boolean equal(MiniContent other) {
        return story == other.story && chapter == other.chapter;
    }

    public int getStory() {
        return story;
    }

    public void setStory(int story) {
        this.story = story;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }
}
