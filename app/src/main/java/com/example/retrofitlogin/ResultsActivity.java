package com.example.retrofitlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private TextView resultsTextView;
    private TextView tokenTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results2);

        resultsTextView = findViewById(R.id.emailResultsTextView);
        tokenTextView = findViewById(R.id.resultsTokenTextView);

        Bundle extras = getIntent().getExtras();
        String email;
        String token;

        if(extras != null) {
            email = extras.getString("email");
            token = extras.getString("token");
            resultsTextView.setText(email);
            tokenTextView.setText(token);
        }

    }
}