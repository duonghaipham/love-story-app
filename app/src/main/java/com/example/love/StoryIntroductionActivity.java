package com.example.love;

import android.os.Bundle;

import com.example.love.adapter.ChapterDataAdapter;
import com.example.love.database.ChapterDatasource;
import com.example.love.database.DbBitmapUtility;
import com.example.love.database.StoryDataSource;
import com.example.love.model.Chapter;
import com.example.love.model.Story;
import com.google.android.material.appbar.CollapsingToolbarLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class StoryIntroductionActivity extends AppCompatActivity {
    private ImageView ivAvatar;
    private Toolbar toolbar;
    private CollapsingToolbarLayout toolBarLayout;
    private ListView lvChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_introduction);

        map();
        setSupportActionBar(toolbar);
        String position = getIntent().getStringExtra("id");

        StoryDataSource storyDataSource = new StoryDataSource(StoryIntroductionActivity.this);
        Story story = storyDataSource.getStoryById(position);

        ivAvatar.setImageBitmap(DbBitmapUtility.getImage(story.getAvatar()));
        toolBarLayout.setTitle(story.getName());

        ChapterDatasource source = new ChapterDatasource(StoryIntroductionActivity.this);
        List<Chapter> chapters = source.getAllChapters(position);
        ChapterDataAdapter adapter = new ChapterDataAdapter(StoryIntroductionActivity.this, chapters);
        lvChapters.setAdapter(adapter);

        source.close();
    }

    private void map() {
        ivAvatar = findViewById(R.id.iv_avatar);
        toolbar = findViewById(R.id.toolbar);
        toolBarLayout = findViewById(R.id.toolbar_layout);
        lvChapters = findViewById(R.id.lv_chapters);
    }
}