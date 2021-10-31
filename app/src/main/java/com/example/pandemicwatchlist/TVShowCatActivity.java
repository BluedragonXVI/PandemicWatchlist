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

public class TVShowCatActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listTVShows = getListView();
        /*ArrayAdapter<TVShow> TVShowListAdapter = new ArrayAdapter<TVShow>(
                this,
                android.R.layout.simple_list_item_1,
                TVShow.tvShows);
        listTVShows.setAdapter(TVShowListAdapter);*/

        // make cursor adaptor
        try {
            SQLiteOpenHelper watchlistDBHelper = new watchlistDBHelper(this,null,null,null);
            db = watchlistDBHelper.getReadableDatabase();
            cursor = db.query("TVSHOW", new String[]{"_id", "NAME"}, null, null, null, null, null);
            CursorAdapter listAdaptor = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                    new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
            listTVShows.setAdapter(listAdaptor);
        } catch(SQLException e){
            Toast toast = Toast.makeText(this,"This database is unreadable.", Toast.LENGTH_SHORT);
            toast.show();
        }
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
        Intent intent = new Intent(TVShowCatActivity.this, TVShowActivity.class);
        intent.putExtra(TVShowActivity.EXTRA_TVSHOW_NUM, (int)id);
        startActivity(intent);
    }
}