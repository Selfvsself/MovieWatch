package com.selfvsself.moviewatch.Model.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.selfvsself.moviewatch.Model.Repository.DbHelper.DbHelper;
import com.selfvsself.moviewatch.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private SQLiteDatabase database;
    private List<Movie> movieList;

    public Repository(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void addMovie(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.MOVIE_TITLE, movie.getTitle());
        contentValues.put(DbHelper.MOVIE_GENRE, movie.getGenre());
        contentValues.put(DbHelper.MOVIE_DESCRIPTION, movie.getDescription());

        database.insert(DbHelper.DATABASE_NAME, null, contentValues);
    }

    public List<Movie> readAll() {
        movieList = new ArrayList<>();
        Cursor cursor = database.query(DbHelper.DATABASE_NAME, null, null,
                null,null,null,null);

        if (cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex(DbHelper.MOVIE_TITLE);
            int genreIndex = cursor.getColumnIndex(DbHelper.MOVIE_GENRE);
            int descriptionIndex = cursor.getColumnIndex(DbHelper.MOVIE_DESCRIPTION);

            do {
                Movie addMovie = new Movie();
                addMovie.setTitle(cursor.getString(titleIndex));
                addMovie.setGenre(cursor.getString(genreIndex));
                addMovie.setDescription(cursor.getString(descriptionIndex));
                movieList.add(addMovie);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return movieList;
    }

    public void deleteMovie(Movie movie) {
        database.delete(DbHelper.DATABASE_NAME,
                DbHelper.MOVIE_TITLE + "= ?", new String[] {movie.getTitle()});
    }
}
