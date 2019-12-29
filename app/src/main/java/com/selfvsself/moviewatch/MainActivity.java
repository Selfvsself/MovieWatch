package com.selfvsself.moviewatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.selfvsself.moviewatch.Helper.RecyclerItemTouchHelper;
import com.selfvsself.moviewatch.Helper.RecyclerItemTouchHelperListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerItemTouchHelperListener {

    private BottomSheetBehavior mbottomSheetBehavior;
    private ImageButton btnAdd, btnAccept;
    private TextInputEditText inputSearch, inputTitle, inputGenre, inputDescription;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private CoordinatorLayout rootLayout;
    List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mbottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        recyclerView = findViewById(R.id.recycler);
        inputSearch = findViewById(R.id.inputSearch);
        inputTitle = findViewById(R.id.inputTitle);
        inputGenre = findViewById(R.id.inputGenre);
        inputDescription = findViewById(R.id.inputDescription);

        movieList.add(new Movie("Терминатор", "Боевик", "Нужно скачать"));
        movieList.add(new Movie("Звездные войны", "Фантастика", "Идет в кино"));
        movieList.add(new Movie("Достать ножи", "Комедия", "Нужно скачать"));
        movieList.add(new Movie("Солнцестояние", "Ужасы", "Нужно скачать"));
        movieList.add(new Movie("Терминатор", "Боевик", "Нужно скачать"));
        movieList.add(new Movie("Звездные войны", "Фантастика", "Идет в кино"));
        movieList.add(new Movie("Достать ножи", "Комедия", "Нужно скачать"));
        movieList.add(new Movie("Солнцестояние", "Ужасы", "Нужно скачать"));
        movieList.add(new Movie("Терминатор", "Боевик", "Нужно скачать"));
        movieList.add(new Movie("Звездные войны", "Фантастика", "Идет в кино"));
        movieList.add(new Movie("Достать ножи", "Комедия", "Нужно скачать"));
        movieList.add(new Movie("Солнцестояние", "Ужасы", "Нужно скачать"));
        movieList.add(new Movie("Терминатор", "Боевик", "Нужно скачать"));
        movieList.add(new Movie("Звездные войны", "Фантастика", "Идет в кино"));
        movieList.add(new Movie("Достать ножи", "Комедия", "Нужно скачать"));
        movieList.add(new Movie("Солнцестояние", "Ужасы", "Нужно скачать"));
        recyclerAdapter = new RecyclerAdapter(this, movieList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recyclerAdapter);
        rootLayout = findViewById(R.id.root_layout);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackRight = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallbackRight).attachToRecyclerView(recyclerView);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(recyclerView);

        btnAdd = findViewById(R.id.btn_add);
        btnAccept = findViewById(R.id.btn_done);

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
                List<Movie> filteredList = new ArrayList<>();
                if (inputSearch.getText() != null) {
                    String searchFilter = inputSearch.getText().toString().toLowerCase();
                    if (searchFilter.equals("")) {
                        recyclerAdapter.setList(movieList);
                        recyclerAdapter.notifyDataSetChanged();
                        return;
                    }
                    for (Movie movie : movieList) {
                        if (movie.getTitle().toLowerCase().contains(searchFilter) ||
                                movie.getGenre().toLowerCase().contains(searchFilter) ||
                                movie.getDescription().toLowerCase().contains(searchFilter)) {
                            filteredList.add(movie);
                        }
                    }
                    recyclerAdapter.setList(filteredList);
                    recyclerAdapter.notifyDataSetChanged();
                }
            }
        });

        mbottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i == BottomSheetBehavior.STATE_COLLAPSED) {
                    btnAdd.setImageResource(R.drawable.ic_add_24dp);
                } else if (i == BottomSheetBehavior.STATE_EXPANDED) {
                    btnAdd.setImageResource(R.drawable.ic_clear_24dp);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                if (mbottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    inputTitle.setText("");
                    inputGenre.setText("");
                    inputDescription.setText("");
                    mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.btn_done:
                Movie addMovie = new Movie();
                addMovie.setTitle(inputTitle.getText().toString());
                addMovie.setGenre(inputGenre.getText().toString());
                addMovie.setDescription(inputDescription.getText().toString());
                movieList.add(addMovie);
                recyclerAdapter.notifyItemInserted(movieList.size() - 1);
                inputTitle.setText("");
                inputGenre.setText("");
                inputDescription.setText("");
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
            String name = movieList.get(viewHolder.getAdapterPosition()).getTitle();

            final Movie deletedMovie = movieList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            recyclerAdapter.removeItem(deletedIndex);

            Snackbar snackbar = Snackbar.make(rootLayout, name + " removed", Snackbar.LENGTH_SHORT);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerAdapter.restoreItem(deletedIndex, deletedMovie);
                }
            });
            snackbar.show();
        }
    }
}
