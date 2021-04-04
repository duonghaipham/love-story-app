package com.example.love;

import android.content.Intent;
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
import android.widget.TextView;

import java.util.List;

public class StoryIntroductionActivity extends AppCompatActivity {
    private ImageView ivAvatar;
    private Toolbar toolbar;
    private CollapsingToolbarLayout toolBarLayout;
    private TextView tvAuthor;
    private TextView tvNumberChapters;
    private ListView lvChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_introduction);
        map();
        setSupportActionBar(toolbar);
        String index = getIntent().getStringExtra("id");    // get index (position) from the previous activity
        loadData(index);

        lvChapters.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(StoryIntroductionActivity.this, ReadingInterfaceActivity.class);
            intent.putExtra("id", position);
            startActivity(intent);
        });
    }

    private void map() {    // map between front-end control and its back-end ones
        ivAvatar = findViewById(R.id.iv_avatar);
        toolbar = findViewById(R.id.toolbar);
        toolBarLayout = findViewById(R.id.toolbar_layout);
        tvAuthor = findViewById(R.id.tv_author);
        tvNumberChapters = findViewById(R.id.tv_number_chapters);
        lvChapters = findViewById(R.id.lv_chapters);
    }

    private void loadData(String position) {    // load data from database and fill into user interface
        // get info about the current story
        StoryDataSource storyDataSource = new StoryDataSource(StoryIntroductionActivity.this);
        Story story = storyDataSource.getStoryById(position);
        ivAvatar.setImageBitmap(DbBitmapUtility.getImage(story.getAvatar()));
        toolBarLayout.setTitle(story.getName());
        tvAuthor.setText(String.format(getString(R.string.author), story.getAuthor()));
        tvNumberChapters.setText(String.format(getString(R.string.number_chapters), story.getNumber_chapters()));

        // get list of chapters of the current story
        ChapterDatasource chapterDatasource = new ChapterDatasource(StoryIntroductionActivity.this);
        List<Chapter> chapters = chapterDatasource.getAllChapters(position);
        ChapterDataAdapter adapter = new ChapterDataAdapter(StoryIntroductionActivity.this, chapters);
        lvChapters.setAdapter(adapter);
    }
}