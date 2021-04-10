package com.example.love;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.love.database.StoryDataSource;
import com.example.love.model.Story;
import com.example.love.adapter.StoryDataAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView svSearch;
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

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                StoryDataSource source = new StoryDataSource(MainActivity.this);
                List<Story> stories = source.getStoriesBySearch(query);
                loadGridStory(stories);
                svSearch.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    loadData();
                }
                return false;
            }
        });
    }

    private void map() {    // map between front-end control and its back-end ones
        svSearch = findViewById(R.id.sv_search);
        gvStories = findViewById(R.id.gv_stories);
    }

    private void loadData() {   // load data from database and fill into user interface
        StoryDataSource source = new StoryDataSource(MainActivity.this);
        List<Story> allStories = source.getAllStories();
        loadGridStory(allStories);
    }

    private void loadGridStory(List<Story> stories) {   // set adapter
        StoryDataAdapter adapter = new StoryDataAdapter(MainActivity.this, stories);
        gvStories.setAdapter(adapter);
    }
}