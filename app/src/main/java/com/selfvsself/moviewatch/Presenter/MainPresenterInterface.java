package com.selfvsself.moviewatch.Presenter;

import com.selfvsself.moviewatch.Model.Movie;
import com.selfvsself.moviewatch.Model.RecyclerAdapter;

import java.util.List;

public interface MainPresenterInterface {

    public List<Movie> getMovieListOnBase();
    public String addMovie(Movie movie);
    public Movie deleteMovie(int indexDeletedMovie);
    public RecyclerAdapter getAdapter();
    public void moviesFilter(String filterStr);
}
