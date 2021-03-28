package com.example.love;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivSearch = findViewById(R.id.iv_search);

        ivSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StoryIntroductionActivity.class);
            startActivity(intent);
        });

    }
}