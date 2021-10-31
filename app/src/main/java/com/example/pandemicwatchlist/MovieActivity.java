package com.example.pandemicwatchlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    // for adding bitmap to canvas
    private Context mContext;
    private Resources mResources;
    private ImageView mImageView;
    private String trailer_url;


    public static final String EXTRA_MOVIE_NUM = "movieNum";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        int movie_number = (Integer)getIntent().getExtras().get(EXTRA_MOVIE_NUM);

        // create a cursor
        try {
            SQLiteOpenHelper watchlistdbhelper = new watchlistDBHelper(this, null,null,2);
            SQLiteDatabase db = watchlistdbhelper.getReadableDatabase();
            Cursor cursor = db.query("MOVIE", new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "TRAILER_URL"}, "_id = ?", new String[]{Integer.toString(movie_number)}, null, null, null);

            // move to the first record with the cursor
            if (cursor.moveToFirst()) {
                String name_text = cursor.getString(0);
                String description_text = cursor.getString(1);
                int photo_id = cursor.getInt(2);
                trailer_url = cursor.getString(3);

                // now add the selected movie's photo
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photo_id);
                photo.setContentDescription(name_text);

                // same for name and description
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(name_text);
                TextView description = (TextView)findViewById(R.id.description);
                description.setText(description_text);

                // implement trailer button youtube intent
                Button trailerBtn = findViewById(R.id.trailer_button);
                trailerBtn.setOnClickListener(this);

            }
            cursor.close();
            db.close();
        } catch (SQLException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        // end of new SQLite code

       /*
        Movie movie = Movie.movies[movie_number];

        // now add the selected movie's photo
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(movie.getImageResID());
        photo.setContentDescription(movie.getName());

        // same for name and description
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(movie.getName());
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(movie.getDescription());

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

        */


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.trailer_button) {
            // make intent for viewing the trailer
            int movie_number = (Integer)getIntent().getExtras().get(EXTRA_MOVIE_NUM);
            Movie movie = Movie.movies[movie_number];
            String trailer_URL = (String)movie.getTrailerURL();
            Intent trailerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailer_URL));
            startActivity(trailerIntent);
        }
        if (v.getId() == R.id.rating_button){
            // make intent for getting the rating

        }
    }
}