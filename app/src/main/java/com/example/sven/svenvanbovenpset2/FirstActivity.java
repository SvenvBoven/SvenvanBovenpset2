package com.example.sven.svenvanbovenpset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    // button to proceed
    public void goToSecond(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
        finish();
    }
}