package com.selfvsself.moviewatch.Presenter;

import android.content.Context;

import com.selfvsself.moviewatch.Model.Movie;
import com.selfvsself.moviewatch.Model.RecyclerAdapter;
import com.selfvsself.moviewatch.Model.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainPresenterInterface {
    private Repository repository;
    private RecyclerAdapter recyclerAdapter;
    private List<Movie> movieList;
    private List<Movie> filteredList;

    public MainPresenter(Context context) {
        repository = new Repository(context);
        movieList = getMovieListOnBase();
        filteredList = new ArrayList<>();
        filteredList.addAll(movieList);
        recyclerAdapter = new RecyclerAdapter(context, filteredList);
    }

    @Override
    public List<Movie> getMovieListOnBase() {
        return repository.readAll();
    }

    @Override
    public String addMovie(Movie movie) {
        String result = null;
        if (checkingUniquenessOfMovieTitle(movie.getTitle()) || validationOfMovieTilte(movie.getTitle())) {
            repository.addMovie(movie);
            movieList.add(movie);
            filteredList.add(movie);
            recyclerAdapter.notifyItemInserted(movieList.size() - 1);
        } else {
            result = "Match found in movie title";
        }
        return result;
    }

    private boolean checkingUniquenessOfMovieTitle(String title) {
        boolean isUniqueness = true;
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                isUniqueness = false;
            }
        }
        return isUniqueness;
    }

    private boolean validationOfMovieTilte(String title) {
        return !title.trim().equals("");
    }

    @Override
    public Movie deleteMovie(int indexDeletedMovie) {
        Movie deleteMovie = movieList.get(indexDeletedMovie);
        repository.deleteMovie(deleteMovie);
        movieList.remove(indexDeletedMovie);
        filteredList.remove(indexDeletedMovie);
        recyclerAdapter.notifyItemRemoved(indexDeletedMovie);
        return deleteMovie;
    }

    @Override
    public RecyclerAdapter getAdapter() {
        return recyclerAdapter;
    }

    @Override
    public void moviesFilter(final String filterStr) {
        filteredList.clear();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).isFiltered(filterStr)) {
                filteredList.add(movieList.get(i));
            }
        }
        recyclerAdapter.notifyDataSetChanged();
    }
}
