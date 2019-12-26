package com.selfvsself.moviewatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheetBehavior mbottomSheetBehavior;
    private ImageButton btnAdd, btnSearch, btnAccept, btnCancel;
    private TextInputEditText inputSearch, inputTitle, inputGenre, inputDescription;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mbottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        recyclerView = findViewById(R.id.recycler);

        List<Movie> movieList = new ArrayList<>();
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
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.hasFixedSize();

        btnAdd = findViewById(R.id.btn_add);
        btnAccept = findViewById(R.id.btn_done);

        btnAdd.setOnClickListener(this);
        btnAccept.setOnClickListener(this);

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
                    mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.btn_done:
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }
}
