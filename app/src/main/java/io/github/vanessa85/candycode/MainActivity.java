package io.github.vanessa85.candycode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Products");

        final ArrayList<String> candyList = new ArrayList<String>();
        candyList.add("Tropical Wave");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item_candy,
                R.id.tvCandy,
                candyList);

        ListView lvCandy = findViewById(R.id.lvCandy);
        lvCandy.setAdapter(adapter);
        lvCandy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.CANDY_NAME, candyList.get(i));
                startActivity(intent);
            }
        });

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://vast-brushlands-23089.herokuapp.com/main/api", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("AsyncHttpClient", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("AsyncHttpClient", responseString);

                Gson gson = new GsonBuilder().create();
                Candy[] candies = gson.fromJson(responseString, Candy[].class);
                adapter.clear();
                for (Candy candy : candies) {
                    adapter.add(candy.name);
                }
            }
        });

    }
}
