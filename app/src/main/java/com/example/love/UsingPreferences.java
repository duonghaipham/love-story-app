package com.example.love;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.love.model.MiniContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UsingPreferences {
    private static String BOOKMARK_KEY = "com.example.love.BOOKMARK_KEY";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private UsingPreferences() { }

    private static SharedPreferences GetSharedPreferences(Context context) {
        return context.getSharedPreferences("com.example.love.PREFERENCE_KEY", Context.MODE_PRIVATE);
    }

    public static ArrayList<MiniContent> getBookmarkArray(Context context) {
        preferences = GetSharedPreferences(context);
        editor = preferences.edit();
        Gson gson = new Gson();
        String storyVsChapter;

        storyVsChapter = preferences.getString(BOOKMARK_KEY, null);
        Type type = new TypeToken<ArrayList<MiniContent>>() {}.getType();
        ArrayList<MiniContent> miniContents = gson.fromJson(storyVsChapter, type);

        return miniContents;
    }

    public static void setBookmarkArray(Context context, ArrayList<MiniContent> miniContents) {
        preferences = GetSharedPreferences(context);

        Gson gson = new Gson();
        String storyVsChapter = gson.toJson(miniContents);
        editor.putString(BOOKMARK_KEY, storyVsChapter);
        editor.apply();
    }

    public static void clearBookmarkArray(Context context) {
        preferences = GetSharedPreferences(context);
        editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
