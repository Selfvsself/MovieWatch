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
import com.google.android.material.textfield.TextInputLayout;
import com.selfvsself.moviewatch.Model.Helper.RecyclerItemTouchHelper;
import com.selfvsself.moviewatch.Model.Helper.RecyclerItemTouchHelperListener;
import com.selfvsself.moviewatch.Model.Movie;
import com.selfvsself.moviewatch.Model.RecyclerAdapter;
import com.selfvsself.moviewatch.Presenter.MainPresenter;
import com.selfvsself.moviewatch.Presenter.MainPresenterInterface;
import com.selfvsself.moviewatch.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerItemTouchHelperListener {

    private BottomSheetBehavior mbottomSheetBehavior;
    private ImageButton btnAdd;
    private TextInputEditText inputSearch, inputGenre, inputDescription;
    private TextInputLayout searchInputLayout;
    private CoordinatorLayout rootLayout;
    private MainPresenterInterface presenter;

    private final String searchInputHint = "Search";
    private final String searchInputHint2 = "Title";
    private final float deltaChangeSearchHint = 1.0f / searchInputHint.length();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mbottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        inputSearch = findViewById(R.id.inputSearch);
        inputGenre = findViewById(R.id.inputGenre);
        inputDescription = findViewById(R.id.inputDescription);
        searchInputLayout = findViewById(R.id.searchInputLayout);
        searchInputLayout.setHint(searchInputHint);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(presenter.getAdapter());
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
                if (mbottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED && inputSearch.getText() != null) {
                    presenter.moviesFilter(inputSearch.getText().toString());
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

                int i = (int) (v / deltaChangeSearchHint);
                int n = i;
                if (n > searchInputHint2.length()) {
                    n = searchInputHint2.length();
                }
                searchInputLayout.setHint(searchInputHint.substring(0, searchInputHint.length() - i) + searchInputHint2.substring(0, n));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                if (mbottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    inputSearch.setText("");
                    inputGenre.setText("");
                    inputDescription.setText("");
                    mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    inputSearch.setText("");
                    inputGenre.setText("");
                    inputDescription.setText("");
                    mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.btn_done:
                if (mbottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    if (inputSearch.getText() != null) {
                        Movie addMovie = new Movie();
                        addMovie.setTitle(inputSearch.getText().toString());
                        addMovie.setGenre(inputGenre.getText().toString());
                        addMovie.setDescription(inputDescription.getText().toString());

                        String resultAdding = presenter.addMovie(addMovie);

                        if (resultAdding != null) {
                            Snackbar snackbar = Snackbar.make(rootLayout, "Incorrect movie title", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    }
                }
                inputSearch.setText("");
                inputGenre.setText("");
                inputDescription.setText("");
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
            final int deletedIndex = viewHolder.getAdapterPosition();

            final Movie deletedMovie = presenter.deleteMovie(deletedIndex);

            Snackbar snackbar = Snackbar.make(rootLayout, deletedMovie.getTitle() + " removed", Snackbar.LENGTH_SHORT);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.addMovie(deletedMovie);
                }
            });
            snackbar.show();
        }
    }
}
