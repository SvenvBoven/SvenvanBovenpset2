package com.example.sven.svenvanbovenpset2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class SecondActivity extends AppCompatActivity {
    private Story verhaal;
    // stores all stories
    String[] verhaaltjes = {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt",
            "madlib3_clothes.txt", "madlib4_dance.txt"};
    Random rand = new Random();
    private int placeholderRemaining;
    private int placeholderTotal;
    private TextView placeholderLeft;
    private EditText woordsoort;
    private Button gotoNextbutton;
    private int randomStory = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if(savedInstanceState != null) {
            randomStory = savedInstanceState.getInt("story");
            Log.d("savestate", Integer.toString(randomStory));
            verhaal = (Story)savedInstanceState.getSerializable("storyClass");
        }
        gotoNextbutton = findViewById(R.id.button3);
        gotoNextbutton.setVisibility(View.INVISIBLE);
        initStory();
    }

    // allows screen rotation and reopening  the app
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("savestate", "saving");
        Log.d("savestate", Integer.toString(randomStory));
        savedInstanceState.putInt("story", randomStory);
        savedInstanceState.putSerializable("storyClass", verhaal);
    }

    // loads up a randomstory
    public void initStory() {
        if (randomStory == -1) {
            randomStory = rand.nextInt(4);
        }

        if(verhaal == null) {
            // idea for assetmanager taken from Stephan de Graaf
            AssetManager assetInputStream = getAssets();
            InputStream loadFile = null;

            try {
                loadFile = assetInputStream.open(verhaaltjes[randomStory]);
                if (loadFile != null)
                    Log.d("test", "yes");
            } catch (IOException e) {
                e.printStackTrace();
            }

            verhaal = new Story(loadFile);
        }
        placeholderLeft = findViewById(R.id.placeholderLeft);
        woordsoort = findViewById(R.id.input);

        woordsoort.setHint(verhaal.getNextPlaceholder());
        modifyPlaceholderCounter();
    }

    // counts the amount of inputs already given and still needed
    public void modifyPlaceholderCounter () {
        placeholderRemaining = verhaal.getPlaceholderRemainingCount();
        placeholderTotal = verhaal.getPlaceholderCount();
        String placeholder = "(" + (placeholderTotal - placeholderRemaining) + "/" + placeholderTotal + ")";
        placeholderLeft.setText(placeholder);
    }

    public void submitWord(View view) {
        Button submitButton = findViewById(R.id.button);
        EditText inputfield = findViewById(R.id.input);
        verhaal.fillInPlaceholder(inputfield.getText().toString());
        woordsoort.setHint(verhaal.getNextPlaceholder());
        modifyPlaceholderCounter();
        inputfield.setText("");
        // makes the editText invisible and the submit button and shows the next button
        if (placeholderRemaining == 0) {
            inputfield.setVisibility(view.INVISIBLE);
            placeholderLeft.setText("press the button to go to next page");
            submitButton.setVisibility(view.INVISIBLE);
            gotoNextbutton.setVisibility(view.VISIBLE);
        }
    }

    // sends app to third
    public void goToThird(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("ourtekst", verhaal.toString());

        startActivity(intent);
        finish();
    }
}