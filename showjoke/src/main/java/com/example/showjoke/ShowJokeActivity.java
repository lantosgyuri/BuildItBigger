package com.example.showjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowJokeActivity extends AppCompatActivity {

    private String joke;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showjoke);

        Intent intent = getIntent();
        if(intent != null) joke = intent.getStringExtra("joke");

        TextView showJokeTextView = findViewById(R.id.show_joke_text_view);
        showJokeTextView.setText(joke);
    }
}
