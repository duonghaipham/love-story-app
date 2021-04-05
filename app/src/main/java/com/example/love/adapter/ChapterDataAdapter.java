package com.example.love.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.love.R;
import com.example.love.model.Chapter;

import java.util.List;

public class ChapterDataAdapter extends BaseAdapter {
    private Activity activity;
    private List<Chapter> chapters;

    public ChapterDataAdapter(Activity activity, List<Chapter> chapters) {
        this.activity = activity;
        this.chapters = chapters;
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Override
    public Chapter getItem(int position) {
        return chapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.chapter_item_background, null);

        TextView tvChapterTitle = convertView.findViewById(R.id.tv_chapter_title);
        tvChapterTitle.setText(chapters.get(position).getChapter());
        return convertView;
    }
}
