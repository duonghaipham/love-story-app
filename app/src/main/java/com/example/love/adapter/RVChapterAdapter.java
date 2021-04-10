package com.example.love.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.love.R;
import com.example.love.model.Chapter;

import java.util.List;

public class RVChapterAdapter extends RecyclerView.Adapter<RVChapterAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Chapter> chapters;

    public RVChapterAdapter(Context context, List<Chapter> chapters) {
        this.inflater = LayoutInflater.from(context);
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.chapter_item_background, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVChapterAdapter.ViewHolder holder, int position) {
        holder.tvChapter.setText(chapters.get(position).getChapter());
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChapter;

        public ViewHolder(View view) {
            super(view);
            tvChapter = view.findViewById(R.id.tv_chapter_title);
        }
    }
}
