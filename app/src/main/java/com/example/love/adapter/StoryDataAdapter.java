package com.example.love.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.love.R;
import com.example.love.database.DbBitmapUtility;
import com.example.love.model.Story;

import java.util.List;

public class StoryDataAdapter extends BaseAdapter {
    private Activity activity;
    private List<Story> stories;

    public StoryDataAdapter(Activity activity, List<Story> stories) {
        this.activity = activity;
        this.stories = stories;
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Object getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.profile_item_background, null);

        ImageView ivAvt = convertView.findViewById(R.id.iv_avt);
        TextView tvName = convertView.findViewById(R.id.tv_name);

        ivAvt.setImageBitmap(DbBitmapUtility.getImage(stories.get(position).getAvatar()));
        tvName.setText(stories.get(position).getName());

        return convertView;
    }
}
