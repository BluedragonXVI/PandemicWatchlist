package com.example.pandemicwatchlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MovieCatActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listMovies = getListView();
        try {
            SQLiteOpenHelper watchlistDBhelper = new watchlistDBHelper(this, null, null, null);
            db = watchlistDBhelper.getReadableDatabase();
            cursor = db.query("MOVIE", new String[]{"_id", "NAME"}, null, null, null, null, null);

            CursorAdapter listAdaptor = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"}, new int[]{android.R.id.text1},0);
            listMovies.setAdapter(listAdaptor);
        } catch (SQLException e){
            Toast toast = Toast.makeText(this,"Database unreadable", Toast.LENGTH_SHORT);
            toast.show();
        }



        /*ListView listMovies = getListView();
        ArrayAdapter<Movie> movieListAdapter = new ArrayAdapter<Movie>(
                this,
                android.R.layout.simple_list_item_1,
                Movie.movies);
        listMovies.setAdapter(movieListAdapter);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(MovieCatActivity.this, MovieActivity.class);
        intent.putExtra(MovieActivity.EXTRA_MOVIE_NUM, (int)id);
        startActivity(intent);
    }
}