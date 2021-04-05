package com.example.love.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.love.model.Chapter;

import java.util.List;
import java.util.Vector;

public class ChapterDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ChapterDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public List<Chapter> getAllChapters(String story) {
        List<Chapter> allChapters = new Vector<>();
        String selectQuery = "SELECT * FROM Chapter WHERE " + DatabaseHelper.CHAPTER_STORY + " = " + story;
        open();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Chapter chapter = new Chapter(cursor.getInt(0),
                                              cursor.getInt(1),
                                              cursor.getString(2),
                                              cursor.getString(3));
                allChapters.add(chapter);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
        return allChapters;
    }

    public Chapter getChapterById(String story, String id) {
        String selectQuery = "SELECT * FROM Chapter " +
                             "WHERE " + DatabaseHelper.CHAPTER_STORY + " = " + story + " " +
                             "AND " + DatabaseHelper.CHAPTER_INDEX + " = " + id;
        open();
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Chapter chapter = new Chapter(cursor.getInt(0),
                                      cursor.getInt(1),
                                      cursor.getString(2),
                                      cursor.getString(3));
        cursor.close();
        close();
        return chapter;
    }
}
