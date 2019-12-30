package com.selfvsself.moviewatch.Model.Repository.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies_database";
    public static final String MOVIE_TITLE  = "_title";
    public static final String MOVIE_GENRE = "genre";
    public static final String MOVIE_DESCRIPTION = "description";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s ( %s TEXT PRIMARY KEY, %s TEXT, %s TEXT );",
                DATABASE_NAME, MOVIE_TITLE, MOVIE_GENRE, MOVIE_DESCRIPTION));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
