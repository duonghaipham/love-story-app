package com.example.love.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.love.R;
import com.example.love.StoryIntroductionActivity;
import com.example.love.adapter.StoryDataAdapter;
import com.example.love.database.StoryDataSource;
import com.example.love.model.Story;

import java.util.List;

public class ListStoriesFragment extends Fragment {
    SearchView svSearch;
    GridView gvStories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_stories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        svSearch = view.findViewById(R.id.sv_search);
        gvStories = view.findViewById(R.id.gv_stories);

        loadData();
        gvStories.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity(), StoryIntroductionActivity.class);
            intent.putExtra("id", position);
            startActivity(intent);
        });

        svSearch.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                StoryDataSource source = new StoryDataSource(getActivity());
                List<Story> stories = source.getStoriesBySearch(query); // query to database and get requested result
                loadGridStory(stories); // load them to grid view
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

    private void loadData() {   // load data from database and fill into user interface
        StoryDataSource source = new StoryDataSource(getActivity());
        List<Story> allStories = source.getAllStories();
        loadGridStory(allStories);
    }

    private void loadGridStory(List<Story> stories) {   // set adapter
        StoryDataAdapter adapter = new StoryDataAdapter(getActivity(), stories);
        gvStories.setAdapter(adapter);
    }
}