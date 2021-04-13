package com.example.love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.love.database.ChapterDataSource;
import com.example.love.database.StoryDataSource;
import com.example.love.model.Chapter;
import com.example.love.model.MiniContent;
import com.example.love.model.Story;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadingInterfaceActivity extends AppCompatActivity {
    private TextView tvChapterBar;
    private TextView tvBody;
    private ImageView ivPrevious;
    private ImageView ivNext;
    private FloatingActionButton fabMore;
    private FloatingActionButton fabBookmark;

    private boolean fabExpanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_interface);
        map();

        int indexStory = getIntent().getIntExtra("index story", 0);
        int indexChapter = getIntent().getIntExtra("index chapter", 0);

        StoryDataSource source = new StoryDataSource(ReadingInterfaceActivity.this);
        Story story = source.getStoryById(String.valueOf(indexStory));
        int numberChapters = story.getNumber_chapters();

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
                ArrayList<MiniContent> miniContents = UsingPreferences.getBookmarkArray(ReadingInterfaceActivity.this) == null
                        ? new ArrayList<>() : UsingPreferences.getBookmarkArray(ReadingInterfaceActivity.this);
                AtomicInteger position = new AtomicInteger(getBookmarkPosition(miniContents, indexStory, indexChapter));
                if (position.get() != -1)
                    fabBookmark.setImageResource(R.drawable.ic_baseline_check_24);

                fabBookmark.show();
                fabExpanded = true;

                fabBookmark.setOnClickListener(sub_v -> {
                    if (position.get() != -1) {
                        miniContents.remove(position.get());
                        position.set(-1);
                        fabBookmark.setImageResource(R.drawable.ic_baseline_bookmarks_24);
                        Toast.makeText(ReadingInterfaceActivity.this, "Đã xóa khỏi dấu trang", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        position.set(miniContents.size());
                        miniContents.add(new MiniContent(indexStory, indexChapter));
                        fabBookmark.setImageResource(R.drawable.ic_baseline_check_24);
                        Toast.makeText(ReadingInterfaceActivity.this, "Đã thêm vào dấu trang", Toast.LENGTH_SHORT).show();
                    }
                    UsingPreferences.clearBookmarkArray(ReadingInterfaceActivity.this);
                    UsingPreferences.setBookmarkArray(ReadingInterfaceActivity.this, miniContents);
                });
            }
            else {
                fabBookmark.hide();
                fabExpanded = false;
            }
        });
    }

    private void map() {    // map between front-end control and its back-end ones
        tvChapterBar = findViewById(R.id.tv_chapter_bar);
        tvBody = findViewById(R.id.tv_body);
        ivPrevious = findViewById(R.id.iv_previous);
        ivNext = findViewById(R.id.iv_next);
        fabMore = findViewById(R.id.fab_more);
        fabBookmark = findViewById(R.id.fab_bookmark);

        fabExpanded = false;
    }

    private void loadData(String indexStory, String indexChapter) { // load data from database and fill into user interface
        ChapterDataSource source = new ChapterDataSource(ReadingInterfaceActivity.this);
        Chapter chapter = source.getChapterById(indexStory, indexChapter);

        tvChapterBar.setText(chapter.getChapter());
        tvBody.setText(chapter.getContent());
    }

    private int getBookmarkPosition(ArrayList<MiniContent> miniContents, int indexStory, int indexChapter) {
        MiniContent mirror = new MiniContent(indexStory, indexChapter);
        for (int i = 0; i < miniContents.size(); i++)
            if (miniContents.get(i).equal(mirror))
                return i;
        return -1;
    }
}