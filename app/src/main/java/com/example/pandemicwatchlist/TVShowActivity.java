package com.example.pandemicwatchlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

// uncomment the onlick implement and multiline commented code, removed for using SQLite
public class TVShowActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_TVSHOW_NUM = "TVShowNum";
    // for adding bitmap to canvas
    private Context mContext;
    private Resources mResources;
    private ImageView mImageView;
    private String trailerURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        int tv_show_number = (Integer) getIntent().getExtras().get(EXTRA_TVSHOW_NUM);

        // create the SQLite helper, cursor
        try {
            SQLiteOpenHelper watchlistHelper = new watchlistDBHelper(this, null, null, null);
            SQLiteDatabase db = watchlistHelper.getReadableDatabase();
            Cursor cursor = db.query("TVSHOW", new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "TRAILER_URL"},
                    "_id = ?", new String[]{Integer.toString(tv_show_number)}, null, null, null);
            // move to first record of cursor
            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageID = cursor.getInt(2);
                trailerURL = cursor.getString(3);

                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);
                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);
                ImageView image = (ImageView) findViewById(R.id.photo);
                image.setImageResource(imageID);
                image.setContentDescription(nameText);

                Button trailerBtn = (Button) findViewById(R.id.trailer_button);
                trailerBtn.setOnClickListener(this);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException SQLe) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        // end of new SQLite code

        /*TVShow tvShow = TVShow.tvShows[tv_show_number];

        // now add the selected movie's photo
        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(tvShow.getImageResID());
        photo.setContentDescription(tvShow.getName());

        // same for name and description
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(tvShow.getName());
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(tvShow.getDescription());

        // implement trailer button youtube intent
        View view = findViewById(R.id.trailer_button);
        view.setOnClickListener(this);

        // experimental bitmap canvas stuff
        mContext = getApplicationContext();
        mResources = getResources();
        mImageView = (ImageView)findViewById(R.id.stars);

        // set a touchlistener for the imageview
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.stars_filled);
                int width = mImageView.getWidth();
                int height = bitmap.getHeight();
                float x_coord = event.getX();
                float x_offset = (x_coord/width) * width;
                // make another bmp to hold source
                Bitmap dstBitmap = Bitmap.createBitmap(width, bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(dstBitmap);
                //canvas.drawBitmap(bitmap, 0,0,null);
                canvas.drawBitmap(bitmap, new Rect(0,0, (int) x_coord, height), new Rect(0,0, (int) x_offset,height), null);
                mImageView.setImageBitmap(dstBitmap);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.trailer_button) {
            // make intent for viewing the trailer
            int tv_show_number = (Integer) getIntent().getExtras().get(EXTRA_TVSHOW_NUM);
            TVShow tvShow = TVShow.tvShows[tv_show_number];
            String trailer_URL = (String) tvShow.getTrailerURL();
            Intent trailerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailer_URL));
            startActivity(trailerIntent);
        }

        if (v.getId() == R.id.rating_button){
            // make intent for getting the rating

        } */


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.trailer_button) {
            // make intent for viewing the trailer
            Intent trailerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerURL));
            startActivity(trailerIntent);
        }
    }
}


