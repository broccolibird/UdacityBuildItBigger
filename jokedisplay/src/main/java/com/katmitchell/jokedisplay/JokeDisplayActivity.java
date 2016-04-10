package com.katmitchell.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        String joke = null;

        if (getIntent() != null && getIntent().hasExtra(EXTRA_JOKE)) {
            joke = getIntent().getStringExtra(EXTRA_JOKE);
        }

        TextView jokeTextView = (TextView) findViewById(R.id.joke_textview);
        jokeTextView.setText((joke == null) ? "There is no joke here :(" : joke);
    }
}
