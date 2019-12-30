package com.selfvsself.moviewatch.Presenter;

import com.selfvsself.moviewatch.Model.Movie;

import java.util.List;

public interface MainPresenterInterface {

    public List<Movie> getMovieListOnBase();
    public void addMovie(Movie movie);
    public void deleteMovie(Movie movie);
}
