package com.example.lanto.builditbigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lanto.builditbigger.paid.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_container, new MainFragment())
                .commit();
    }
}
