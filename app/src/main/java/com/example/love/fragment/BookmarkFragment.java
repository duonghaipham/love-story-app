package com.example.love.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.love.R;
import com.example.love.StoryIntroductionActivity;
import com.example.love.adapter.RVBookmarkAdapter;
import com.example.love.model.MiniContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BookmarkFragment extends Fragment {
    private RecyclerView rvStoryChapter;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        rvStoryChapter = view.findViewById(R.id.rv_story_chapter_bookmark);
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        String key = "com.example.love.BOOKMARK_KEY";
        preferences = getActivity().getSharedPreferences("com.example.love.PREFERENCE_KEY", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String storyVsChapter = preferences.getString(key, null);
        Type type = new TypeToken<ArrayList<MiniContent>>() {}.getType();
        ArrayList<MiniContent> miniContents = gson.fromJson(storyVsChapter, type);

        if (miniContents != null) {
            RVBookmarkAdapter adapter = new RVBookmarkAdapter(getActivity(), miniContents);
            rvStoryChapter.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvStoryChapter.setAdapter(adapter);
        }
    }
}