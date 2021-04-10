package com.example.love.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LoveStory";

    public static final String TABLE_STORY = "Story";  // table story, below are its fields
    public static final String STORY_ID = "id";
    public static final String STORY_NAME = "name";
    public static final String STORY_AUTHOR = "author";
    public static final String STORY_NUMBER_CHAPTERS = "number_chapters";
    public static final String STORY_AVATAR = "avatar";

    public static final String VIRTUAL_TABLE_STORY = "VirtualStory";

    public static final String TABLE_CHAPTER = "Chapter";  // table chapter, below are its fields
    public static final String CHAPTER_STORY = "story";
    public static final String CHAPTER_INDEX = "id";
    public static final String CHAPTER_NAME = "chapter";
    public static final String CHAPTER_CONTENT = "content";

    private static final String CREATE_TABLE_STORY = "CREATE TABLE " + TABLE_STORY + " (" +
                                                     STORY_ID + " INTEGER," +
                                                     STORY_NAME + " TEXT," +
                                                     STORY_AUTHOR + " TEXT," +
                                                     STORY_NUMBER_CHAPTERS + " INTEGER," +
                                                     STORY_AVATAR + " BLOB," +
                                                     "PRIMARY KEY (" + STORY_ID +"))";

    private static final String CREATE_VIRTUAL_TABLE_STORY = "CREATE VIRTUAL TABLE " + VIRTUAL_TABLE_STORY + " USING FTS3 (" +
                                                             STORY_ID +  "," +
                                                             STORY_NAME + "," +
                                                             STORY_AUTHOR + ")";

    private static final String CREATE_TABLE_CHAPTER = "CREATE TABLE " + TABLE_CHAPTER + "(" +
                                                        CHAPTER_STORY + " INTEGER," +
                                                        CHAPTER_INDEX + " INTEGER," +
                                                        CHAPTER_NAME + " TEXT," +
                                                        CHAPTER_CONTENT + " TEXT," +
                                                        "PRIMARY KEY (" + CHAPTER_STORY + ", " + CHAPTER_INDEX + ")," +
                                                        "FOREIGN KEY(" + CHAPTER_STORY + ") REFERENCES " + TABLE_STORY + "(" + STORY_ID + "))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STORY);
        db.execSQL(CREATE_VIRTUAL_TABLE_STORY);
        db.execSQL(CREATE_TABLE_CHAPTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAPTER);
        onCreate(db);
    }
}
