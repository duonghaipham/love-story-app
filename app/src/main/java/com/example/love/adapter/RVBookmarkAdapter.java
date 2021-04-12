package com.example.love.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.love.R;
import com.example.love.database.ChapterDataSource;
import com.example.love.database.StoryDataSource;
import com.example.love.model.Chapter;
import com.example.love.model.MiniContent;
import com.example.love.model.Story;

import java.util.ArrayList;


public class RVBookmarkAdapter extends RecyclerView.Adapter<RVBookmarkAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MiniContent> miniContents;

    public RVBookmarkAdapter(Context context, ArrayList<MiniContent> miniContents) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.miniContents = miniContents;
    }

    @NonNull
    @Override
    public RVBookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bookmark_content_item_background, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVBookmarkAdapter.ViewHolder holder, int position) {
        String indexStory = String.valueOf(miniContents.get(position).getStory());
        String indexChapter = String.valueOf(miniContents.get(position).getChapter());

        StoryDataSource storySource = new StoryDataSource(context);
        Story story = storySource.getStoryById(indexStory);
        ChapterDataSource chapterSource = new ChapterDataSource(context);
        Chapter chapter = chapterSource.getChapterById(indexStory, indexChapter);

        holder.tvStory.setText(story.getName());
        holder.tvChapter.setText(chapter.getChapter());
    }

    @Override
    public int getItemCount() {
        return miniContents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvStory;
        private TextView tvChapter;

        public ViewHolder(View view) {
            super(view);
            tvStory = view.findViewById(R.id.tv_story_first);
            tvChapter = view.findViewById(R.id.tv_chapter_second);
        }
    }
}
