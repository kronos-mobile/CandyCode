package io.github.vanessa85.candycode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String CANDY_NAME = "candy_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvName = findViewById(R.id.tvName);
        String candyName = "";

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(CANDY_NAME)) {
            candyName = intent.getStringExtra(CANDY_NAME);
        }

        tvName.setText(candyName);

    }
}
