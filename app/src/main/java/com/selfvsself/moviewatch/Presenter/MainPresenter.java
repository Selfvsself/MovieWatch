package com.selfvsself.moviewatch.Presenter;

import android.content.Context;

import com.selfvsself.moviewatch.Model.Movie;

import java.util.List;

import com.selfvsself.moviewatch.Model.RecyclerAdapter;
import com.selfvsself.moviewatch.Model.Repository.*;

public class MainPresenter implements MainPresenterInterface {
    private Repository repository;
    private RecyclerAdapter recyclerAdapter;
    private List<Movie> movieList;

    public MainPresenter(Context context) {
        repository = new Repository(context);
        movieList = getMovieListOnBase();
        recyclerAdapter = new RecyclerAdapter(context, movieList);
    }

    @Override
    public List<Movie> getMovieListOnBase() {
        return repository.readAll();
    }

    @Override
    public void addMovie(Movie movie) {
        repository.addMovie(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        repository.deleteMovie(movie);
    }
}
