package com.example.love;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import com.example.love.adapter.ChapterDataAdapter;
import com.example.love.database.ChapterDataSource;
import com.example.love.database.DbBitmapUtility;
import com.example.love.database.StoryDataSource;
import com.example.love.model.Chapter;
import com.example.love.model.Story;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        int indexStory = getIntent().getIntExtra("id", 0);    // get index (position) from the previous activity
        loadData(String.valueOf(indexStory));

        lvChapters.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(StoryIntroductionActivity.this, ReadingInterfaceActivity.class);
            intent.putExtra("index story", indexStory);
            intent.putExtra("index chapter", position);
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

    private void loadData(String indexStory) {    // load data from database and fill into user interface
        // get info about the current story
        StoryDataSource storyDataSource = new StoryDataSource(StoryIntroductionActivity.this);
        Story story = storyDataSource.getStoryById(indexStory);
        ivAvatar.setImageBitmap(DbBitmapUtility.getImage(story.getAvatar()));
        toolBarLayout.setTitle(story.getName());
        tvAuthor.setText(String.format(getString(R.string.author), story.getAuthor()));
        tvNumberChapters.setText(String.format(getString(R.string.number_chapters), story.getNumber_chapters()));

        // get list of chapters of the current story
        ChapterDataSource chapterDatasource = new ChapterDataSource(StoryIntroductionActivity.this);
        List<Chapter> chapters = chapterDatasource.getAllChapters(indexStory);
        ChapterDataAdapter adapter = new ChapterDataAdapter(StoryIntroductionActivity.this, chapters);
        lvChapters.setAdapter(adapter);
    }
}