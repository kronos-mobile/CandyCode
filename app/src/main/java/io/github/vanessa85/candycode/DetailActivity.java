package io.github.vanessa85.candycode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String CANDY = "candy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvDescription = findViewById(R.id.tvDescription);
        ImageView imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(CANDY)) {
            Candy candy = (Candy) intent.getSerializableExtra(CANDY);

            tvName.setText(candy.name);
            tvPrice.setText(candy.price);
            tvDescription.setText(candy.description);

            Picasso.with(this).load(candy.image).into(imageView);
        }

    }
}
