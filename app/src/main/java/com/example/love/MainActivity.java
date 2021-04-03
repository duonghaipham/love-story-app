package com.example.love;

import androidx.appcompat.app.AppCompatActivity;

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

        ivSearch = findViewById(R.id.iv_search);
        gvStories = findViewById(R.id.gv_stories);

        StoryDataSource source = new StoryDataSource(MainActivity.this);
        List<Story> allStories = source.getAllStories();
        source.close();

        StoryDataAdapter adapter = new StoryDataAdapter(MainActivity.this, allStories);
        gvStories.setAdapter(adapter);
    }
}