package com.example.love.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.love.model.Story;

import java.util.List;
import java.util.Vector;

public class StoryDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public StoryDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Story story) {
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.STORY_ID, story.getId());
        values.put(DatabaseHelper.STORY_NAME, story.getName());
        values.put(DatabaseHelper.STORY_AUTHOR, story.getAuthor());
        values.put(DatabaseHelper.STORY_NUMBER_CHAPTERS, story.getNumber_chapters());
        values.put(DatabaseHelper.STORY_AVATAR, story.getAvatar());

        database.insert("Story", null, values);
        database.close();
    }

    public List<Story> getAllStories() {
        List<Story> allStories = new Vector<>();
        String selectQuery = "SELECT * FROM Story";
        open();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story(cursor.getString(0),
                                        cursor.getString(1),
                                        cursor.getString(2),
                                        cursor.getInt(3),
                                        cursor.getBlob(4));
                allStories.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allStories;
    }
}
