package com.example.sven.svenvanbovenpset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    // takes text generated from the story and the user inputs and combines them.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        String receivedText = intent.getStringExtra("ourtekst");

        TextView textview = findViewById(R.id.madlibs);
        textview.setText(receivedText);
    }
}
