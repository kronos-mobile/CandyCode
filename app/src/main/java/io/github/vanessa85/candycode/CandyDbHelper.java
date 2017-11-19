package io.github.vanessa85.candycode;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vanessa on 11/19/17.
 */

public class CandyDbHelper extends SQLiteOpenHelper {

    public CandyDbHelper(Context context) {
        super(context, CandyContract.DB_NAME, null, CandyContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CandyContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(CandyContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
