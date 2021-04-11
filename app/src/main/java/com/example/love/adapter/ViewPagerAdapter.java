package com.example.love.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.love.fragment.BookmarkFragment;
import com.example.love.fragment.ListStoriesFragment;
import com.example.love.fragment.StarFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new BookmarkFragment();
                break;
            case 2:
                fragment = new StarFragment();
                break;
            default:
                fragment = new ListStoriesFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case 1:
                title = "DẤU TRANG";
                break;
            case 2:
                title = "ĐÃ THÍCH";
                break;
            default:
                title = "DANH SÁCH";
                break;
        }
        return title;
    }
}
