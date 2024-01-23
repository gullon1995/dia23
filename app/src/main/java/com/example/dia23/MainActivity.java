package com.example.dia23;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put( FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "carlos");
        values.put( FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "gullon");

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert( FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        Cursor mCount = db.rawQuery( "SELECT COUNT(*) FROM "+FeedReaderContract.FeedEntry.TABLE_NAME, null );
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        TextView t = findViewById( R.id.textView );

        String texto=" ";
        Cursor c = db.rawQuery( "SELECT * FROM "+FeedReaderContract.FeedEntry.TABLE_NAME, null );
        while (c.moveToNext()){
            texto+=c.getString(1)+" ";
            texto+=c.getString( 2 )+" ";
        }
        c.close();
        t.setText("tenemos "+count+"y los registros son "+texto);
    }
}