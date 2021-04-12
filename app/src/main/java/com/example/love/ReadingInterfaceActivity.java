package com.example.love;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.love.database.ChapterDataSource;
import com.example.love.model.Chapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReadingInterfaceActivity extends AppCompatActivity {
    private TextView tvChapterBar;
    private TextView tvBody;
    private ImageView ivPrevious;
    private ImageView ivNext;
    private FloatingActionButton fabMore;
    private FloatingActionButton fabStar;
    private FloatingActionButton fabBookmark;
    private boolean fabExpanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_interface);
        map();

        int indexStory = getIntent().getIntExtra("index story", 0);
        int indexChapter = getIntent().getIntExtra("index chapter", 0);
        int numberChapters = getIntent().getIntExtra("number chapters", 0);
        loadData(String.valueOf(indexStory), String.valueOf(indexChapter));

        ivPrevious.setOnClickListener(v -> {
            if (indexChapter > 0) {
                Intent intent = new Intent(ReadingInterfaceActivity.this, ReadingInterfaceActivity.class);
                intent.putExtra("index story", indexStory);
                intent.putExtra("index chapter", indexChapter - 1);
                intent.putExtra("number chapters", numberChapters);
                startActivity(intent);
            }
        });

        ivNext.setOnClickListener(v -> {
            if (indexChapter < numberChapters - 1) {
                Intent intent = new Intent(ReadingInterfaceActivity.this, ReadingInterfaceActivity.class);
                intent.putExtra("index story", indexStory);
                intent.putExtra("index chapter", indexChapter + 1);
                intent.putExtra("number chapters", numberChapters);
                startActivity(intent);
            }
        });

        fabMore.setOnClickListener(v -> {
            if (!fabExpanded) {
                fabStar.show();
                fabBookmark.show();
                fabExpanded = true;
            }
            else {
                fabStar.hide();
                fabBookmark.hide();
                fabExpanded = false;
            }
        });

        fabStar.setOnClickListener(v -> {
            Toast.makeText(this, "Đã thích", Toast.LENGTH_SHORT).show();
        });

        fabBookmark.setOnClickListener(v -> {
            Toast.makeText(this, "Đã đánh dấu", Toast.LENGTH_SHORT).show();
        });
    }

    private void map() {    // map between front-end control and its back-end ones
        tvChapterBar = findViewById(R.id.tv_chapter_bar);
        tvBody = findViewById(R.id.tv_body);
        ivPrevious = findViewById(R.id.iv_previous);
        ivNext = findViewById(R.id.iv_next);
        fabMore = findViewById(R.id.fab_more);
        fabStar = findViewById(R.id.fab_star);
        fabBookmark = findViewById(R.id.fab_bookmark);

        fabExpanded = false;
    }

    private void loadData(String indexStory, String indexChapter) { // load data from database and fill into user interface
        ChapterDataSource source = new ChapterDataSource(ReadingInterfaceActivity.this);
        Chapter chapter = source.getChapterById(indexStory, indexChapter);

        tvChapterBar.setText(chapter.getChapter());
        tvBody.setText(chapter.getContent());
    }
}