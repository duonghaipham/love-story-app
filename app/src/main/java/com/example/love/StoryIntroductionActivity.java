package com.example.love;

import android.content.Intent;
import android.os.Bundle;

import com.example.love.adapter.RVChapterAdapter;
import com.example.love.adapter.RecyclerItemClickListener;
import com.example.love.database.ChapterDataSource;
import com.example.love.database.DbBitmapUtility;
import com.example.love.database.StoryDataSource;
import com.example.love.model.Chapter;
import com.example.love.model.Story;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StoryIntroductionActivity extends AppCompatActivity {
    private ImageView ivAvatar;
    private Toolbar toolbar;
    private CollapsingToolbarLayout toolBarLayout;
    private TextView tvAuthor;
    private TextView tvNumberChapters;
    private RecyclerView rvChapters;
    private int numberChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_introduction);
        map();
        setSupportActionBar(toolbar);
        int indexStory = getIntent().getIntExtra("id", 0);    // get index (position) from the previous activity
        loadData(String.valueOf(indexStory));

        rvChapters.addOnItemTouchListener(
                new RecyclerItemClickListener(StoryIntroductionActivity.this, rvChapters, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(StoryIntroductionActivity.this, ReadingInterfaceActivity.class);
                        intent.putExtra("index story", indexStory);
                        intent.putExtra("index chapter", position);
                        intent.putExtra("number chapters", numberChapters);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) { }
                }));
    }

    private void map() {    // map between front-end control and its back-end ones
        ivAvatar = findViewById(R.id.iv_avatar);
        toolbar = findViewById(R.id.toolbar);
        toolBarLayout = findViewById(R.id.toolbar_layout);
        tvAuthor = findViewById(R.id.tv_author);
        tvNumberChapters = findViewById(R.id.tv_number_chapters);
        rvChapters = findViewById(R.id.rv_chapters);
    }

    private void loadData(String indexStory) {    // load data from database and fill into user interface
        // get info about the current story
        StoryDataSource storyDataSource = new StoryDataSource(StoryIntroductionActivity.this);
        Story story = storyDataSource.getStoryById(indexStory);
        ivAvatar.setImageBitmap(DbBitmapUtility.getImage(story.getAvatar()));
        toolBarLayout.setTitle(story.getName());
        tvAuthor.setText(String.format(getString(R.string.author), story.getAuthor()));
        tvNumberChapters.setText(String.format(getString(R.string.number_chapters), story.getNumber_chapters()));
        numberChapters = story.getNumber_chapters();

        // get list of chapters of the current story
        ChapterDataSource chapterDatasource = new ChapterDataSource(StoryIntroductionActivity.this);
        List<Chapter> chapters = chapterDatasource.getAllChapters(indexStory);
        RVChapterAdapter adapter = new RVChapterAdapter(StoryIntroductionActivity.this, chapters);
        rvChapters.setLayoutManager(new LinearLayoutManager(StoryIntroductionActivity.this));
        rvChapters.setAdapter(adapter);
    }
}