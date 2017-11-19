package io.github.vanessa85.candycode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Products");

        ArrayList<String> candyList = new ArrayList<String>();
        candyList.add("Tropical Wave");
        candyList.add("Berry Bouncer");
        candyList.add("Grape Gummer");
        candyList.add("Apple of My Eye");
        candyList.add("Much Minty");
        candyList.add("So Fresh");
        candyList.add("Sassy Sandwich Cookie");
        candyList.add("Uni-pop");
        candyList.add("Straberry Surprise");
        candyList.add("Wish Upon a Start");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item_candy,
                R.id.tvCandy,
                candyList);

        ListView lvCandy = findViewById(R.id.lvCandy);
        lvCandy.setAdapter(adapter);
        lvCandy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

    }
}
