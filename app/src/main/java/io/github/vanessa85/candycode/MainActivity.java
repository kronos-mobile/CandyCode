package io.github.vanessa85.candycode;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private Candy[] candies;
    private CandyDbHelper candyDbHelper = new CandyDbHelper(this);
    private CursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Products");

        SQLiteDatabase db = candyDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM candy", null);
        adapter = new CandyCursorAdapter(this, cursor);


        ListView lvCandy = findViewById(R.id.lvCandy);
        lvCandy.setAdapter(adapter);
        lvCandy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.POSITION, i);

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
                candies = gson.fromJson(responseString, Candy[].class);
                addCandiesToDatabase(candies);

                // updating cursor adapter with the latest database entries
                SQLiteDatabase db = candyDbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM candy", null);
                adapter.changeCursor(cursor);
            }
        });

    }

    public void addCandiesToDatabase(Candy[] candies) {
        SQLiteDatabase db = candyDbHelper.getWritableDatabase();

        for (Candy candy : candies) {
            ContentValues values = new ContentValues();
            values.put(CandyContract.CandyEntry.COLUMN_NAME_NAME, candy.name);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_PRICE, candy.price);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_DESCRIPTION, candy.description);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_IMAGE, candy.image);

            db.insert(CandyContract.CandyEntry.TABLE_NAME, null, values);
        }
    }
}
