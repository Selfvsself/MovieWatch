package com.selfvsself.moviewatch.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.selfvsself.moviewatch.Model.Helper.*;
import com.selfvsself.moviewatch.Model.Movie;
import com.selfvsself.moviewatch.Model.RecyclerAdapter;
import com.selfvsself.moviewatch.Presenter.*;
import com.selfvsself.moviewatch.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerItemTouchHelperListener {

    private BottomSheetBehavior mbottomSheetBehavior;
    private ImageButton btnAdd;
    private TextInputEditText inputSearch, inputTitle, inputGenre, inputDescription;
    private RecyclerAdapter recyclerAdapter;
    private CoordinatorLayout rootLayout;
    private List<Movie> movieList;
    private List<Movie> filteredMovieList;
    private MainPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mbottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        inputSearch = findViewById(R.id.inputSearch);
        inputTitle = findViewById(R.id.inputTitle);
        inputGenre = findViewById(R.id.inputGenre);
        inputDescription = findViewById(R.id.inputDescription);

        movieList = presenter.getMovieListOnBase();
        filteredMovieList = new ArrayList<>(movieList);

        recyclerAdapter = new RecyclerAdapter(this, filteredMovieList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recyclerAdapter);
        rootLayout = findViewById(R.id.root_layout);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackRight = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallbackRight).attachToRecyclerView(recyclerView);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(recyclerView);

        btnAdd = findViewById(R.id.btn_add);
        ImageButton btnAccept = findViewById(R.id.btn_done);

        btnAdd.setOnClickListener(this);
        btnAccept.setOnClickListener(this);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filteredMovieList = new ArrayList<>(movieList);
                if (inputSearch.getText() != null) {
                    String searchFilter = inputSearch.getText().toString().toLowerCase();
                    filterMainMovieList(searchFilter);
                }
            }
        });

        mbottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                btnAdd.setRotation(v * 45);
            }
        });
    }

    private void filterMainMovieList(String searchString) {
        filteredMovieList.clear();
        recyclerAdapter.notifyDataSetChanged();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTitle().toLowerCase().contains(searchString) ||
                    movieList.get(i).getGenre().toLowerCase().contains(searchString) ||
                    movieList.get(i).getDescription().toLowerCase().contains(searchString)) {
                filteredMovieList.add(movieList.get(i));
                recyclerAdapter.notifyItemInserted(filteredMovieList.size() - 1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                if (mbottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    inputTitle.requestFocus();
                } else {
                    inputTitle.setText("");
                    inputGenre.setText("");
                    inputDescription.setText("");
                    inputSearch.requestFocus();
                    mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.btn_done:
                String nameNewMovie;
                if (inputTitle.getText() != null) {
                    nameNewMovie = inputTitle.getText().toString();
                } else {
                    nameNewMovie = "";
                }
                if (nameNewMovie.length() > 0 && !checkMatches(nameNewMovie)) {
                    Movie addMovie = new Movie();
                    addMovie.setTitle(inputTitle.getText().toString());
                    addMovie.setGenre(inputGenre.getText().toString());
                    addMovie.setDescription(inputDescription.getText().toString());
                    movieList.add(addMovie);
                    presenter.addMovie(addMovie);
                    recyclerAdapter.notifyItemInserted(movieList.size() - 1);
                } else {
                    Snackbar snackbar = Snackbar.make(rootLayout, "\n" +
                            "incorrect movie title", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                inputTitle.setText("");
                inputGenre.setText("");
                inputDescription.setText("");
                inputSearch.requestFocus();
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }

    private boolean checkMatches(String nameNewMovie) {
        boolean isMatches = false;
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(nameNewMovie)) {
                isMatches = true;
            }
        }
        return isMatches;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
            String name = movieList.get(viewHolder.getAdapterPosition()).getTitle();

            final Movie deletedMovie = movieList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            presenter.deleteMovie(deletedMovie);
            recyclerAdapter.removeItem(deletedIndex);

            Snackbar snackbar = Snackbar.make(rootLayout, name + " removed", Snackbar.LENGTH_SHORT);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.addMovie(deletedMovie);
                    recyclerAdapter.restoreItem(deletedMovie);
                }
            });
            snackbar.show();
        }
    }
}
