package com.example.pandemicwatchlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class watchlistDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PandemicWatchlist";
    private static final int DB_VERSION = 2;

    public watchlistDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, Integer version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0 , DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE MOVIE (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "NAME TEXT, " + "DESCRIPTION TEXT, " + "IMAGE_RESOURCE_ID INTEGER, " + "TRAILER_URL TEXT)");

            db.execSQL("CREATE TABLE TVSHOW (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT, " + "DESCRIPTION TEXT, " + "IMAGE_RESOURCE_ID INTEGER, " + "TRAILER_URL TEXT)");

            insertMovie(db, "Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given " +
                    "the inverse task of planting an idea into the mind of a C.E.O.", R.drawable.inception, "https://www.youtube.com/watch?v=YoHD9XEInc0");
            insertMovie(db, "Harry Potter and the Chamber of Secrets", "An ancient prophecy seems to be coming true when a mysterious " +
                    "presence begins stalking the corridors of a school of magic and leaving its victims paralyzed.", R.drawable.chamberofsecrets, "https://www.youtube.com/watch?v=1bq0qff4iF8");
            insertMovie(db, "Breaking Dawn Part 2", "After the birth of Renesmee/Nessie, the Cullens gather other vampire clans in order to protect " +
                    "the child from a false allegation that puts the family in front of the Volturi.", R.drawable.breakingdawnpart2, "https://www.youtube.com/watch?v=ti0H-bvMi3I");

            insertTVShow(db, "Black Clover", "In a world where magic is everything, Asta and Yuno are both found abandoned at a church on the same day. While Yuno is gifted " +
                    "with exceptional magical powers, Asta is the only one in this world without any. Being opposite but good rivals, Yuno and Asta are ready for the hardest of " +
                    "challenges to achieve their common dream: to be the Wizard King.", R.drawable.black_clover, "https://www.youtube.com/watch?v=uaDeobqouGQ");
            insertTVShow(db, "Demon Slayer", "After a demon attack leaves his family slain and his sister cursed, Tanjiro embarks upon a perilous journey to find " +
                    "a cure and avenge those he's lost.", R.drawable.demon_slayer, "https://www.youtube.com/watch?v=VQGCKyvzIM4");
            insertTVShow(db, "Naruto", "Naruto Uzumaki, a mischievous adolescent ninja, struggles as he searches for recognition and " +
                    "dreams of becoming the Hokage, the village's leader and strongest ninja.", R.drawable.naruto, "https://www.youtube.com/watch?v=j2hiC9BmJlQ");
        }
        if (oldVersion < 2){
            db.execSQL("ALTER TABLE MOVIE ADD COLUMN RATING NUMERIC");
        }
    }

    private void insertTVShow(SQLiteDatabase db, String name, String description, int imageID, String trailerURL) {
        ContentValues tvShowValues = new ContentValues();
        tvShowValues.put("NAME", name);
        tvShowValues.put("DESCRIPTION", description);
        tvShowValues.put("IMAGE_RESOURCE_ID", imageID);
        tvShowValues.put("TRAILER_URL", trailerURL);
        db.insert("TVSHOW", null, tvShowValues);

    }

    private void insertMovie(SQLiteDatabase db, String name, String description, int imageID, String trailerURL) {
        ContentValues movieValues = new ContentValues();
        movieValues.put("NAME", name);
        movieValues.put("DESCRIPTION", description);
        movieValues.put("IMAGE_RESOURCE_ID", imageID);
        movieValues.put("TRAILER_URL", trailerURL);
        db.insert("MOVIE", null, movieValues);
    }
}
