package com.example.love.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.love.model.Chapter;

import java.util.List;
import java.util.Vector;

public class ChapterDatasource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ChapterDatasource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<Chapter> getAllChapters(String story) {
        List<Chapter> allChapters = new Vector<>();
        String selectQuery = "SELECT * FROM Chapter WHERE " + DatabaseHelper.CHAPTER_STORY + " = " + story;
        open();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Chapter chapter = new Chapter(cursor.getString(0),
                                              cursor.getString(1),
                                              cursor.getString(2));
                allChapters.add(chapter);
            } while (cursor.moveToNext());
        }
        return allChapters;
    }
}
