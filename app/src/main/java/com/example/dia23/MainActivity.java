package com.example.dia23;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int contador=10;
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

        contador++;
        Button botonA = findViewById( R.id.boton1 );
        Button botonE = findViewById( R.id.boton2 );
        EditText Tnombre = findViewById( R.id.editText1  );
        EditText Tapellido = findViewById( R.id.editText2 ) ;
        botonA.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                String nombreA = String.valueOf( Tnombre.getText() );
                String apellidoA = String.valueOf( Tapellido.getText() );
                Cursor a = db.rawQuery( "INSERT INTO "+FeedReaderContract.FeedEntry.TABLE_NAME+" VALUES ("+contador+", "+Tnombre+", "+Tapellido+")", null );
                a.close();
            }
        } );
    }
}