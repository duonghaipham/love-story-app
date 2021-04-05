package com.example.love.model;

public class Story {
    private int id;
    private String name;
    private String author;
    private int number_chapters;
    private byte[] avatar;

    public Story(int id, String name, String author, int number_chapters, byte[] avatar) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.number_chapters = number_chapters;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumber_chapters() {
        return number_chapters;
    }

    public void setNumber_chapters(int number_chapters) {
        this.number_chapters = number_chapters;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
