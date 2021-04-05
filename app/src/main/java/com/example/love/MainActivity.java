package com.example.love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.love.database.StoryDataSource;
import com.example.love.model.Story;
import com.example.love.adapter.StoryDataAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView ivSearch;
    private GridView gvStories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();
        loadData();

        gvStories.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, StoryIntroductionActivity.class);
            intent.putExtra("id", position);
            startActivity(intent);
        });
    }

    private void map() {    // map between front-end control and its back-end ones
        ivSearch = findViewById(R.id.iv_search);
        gvStories = findViewById(R.id.gv_stories);
    }

    private void loadData() {   // load data from database and fill into user interface
        StoryDataSource source = new StoryDataSource(MainActivity.this);
        List<Story> allStories = source.getAllStories();
        StoryDataAdapter adapter = new StoryDataAdapter(MainActivity.this, allStories);
        gvStories.setAdapter(adapter);
    }
}