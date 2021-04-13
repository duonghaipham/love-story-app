package com.example.love.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.love.R;
import com.example.love.ReadingInterfaceActivity;
import com.example.love.UsingPreferences;
import com.example.love.adapter.RVBookmarkAdapter;
import com.example.love.adapter.RecyclerItemClickListener;
import com.example.love.model.MiniContent;

import java.util.ArrayList;

public class BookmarkFragment extends Fragment {
    private RecyclerView rvStoryChapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        rvStoryChapter = view.findViewById(R.id.rv_story_chapter_bookmark);
        loadData();

        rvStoryChapter.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), rvStoryChapter, (sub_view, position) -> {
                    ArrayList<MiniContent> miniContents = UsingPreferences.getBookmarkArray(getActivity());
                    Intent intent = new Intent(getActivity(), ReadingInterfaceActivity.class);
                    intent.putExtra("index story", miniContents.get(position).getStory());
                    intent.putExtra("index chapter", miniContents.get(position).getChapter());
                    startActivity(intent);
                }));
    }

    @Override
    public void onResume() {    // refresh recycler view in need
        super.onResume();
        loadData();
    }

    private void loadData() {   // load data to view
        ArrayList<MiniContent> miniContents = UsingPreferences.getBookmarkArray(getActivity());
        if (miniContents != null) {
            RVBookmarkAdapter adapter = new RVBookmarkAdapter(getActivity(), miniContents);
            rvStoryChapter.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvStoryChapter.setAdapter(adapter);
        }
    }
}