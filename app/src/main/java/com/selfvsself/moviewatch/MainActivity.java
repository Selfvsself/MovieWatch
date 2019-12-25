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

        btnAdd = findViewById(R.id.btn_add);
        btnSearch = findViewById(R.id.btn_search);
        btnAccept = findViewById(R.id.btn_accept);
        btnCancel = findViewById(R.id.btn_cancel);
        recyclerView = findViewById(R.id.recycler);
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Терминатор", "Боевик", "Скачать"));
        movieList.add(new Movie("Звездные войны", "Фантастика", "В кино"));
        movieList.add(new Movie("Достать ножи", "Комедия", "Скачать"));
        movieList.add(new Movie("Солнцестояние", "Ужасы", "Скачать"));
        movieList.add(new Movie("Терминатор", "Боевик", "Скачать"));
        movieList.add(new Movie("Звездные войны", "Фантастика", "В кино"));
        movieList.add(new Movie("Достать ножи", "Комедия", "Скачать"));
        movieList.add(new Movie("Солнцестояние", "Ужасы", "Скачать"));
        movieList.add(new Movie("Терминатор", "Боевик", "Скачать"));
        movieList.add(new Movie("Звездные войны", "Фантастика", "В кино"));
        movieList.add(new Movie("Достать ножи", "Комедия", "Скачать"));
        movieList.add(new Movie("Солнцестояние", "Ужасы", "Скачать"));
        movieList.add(new Movie("Терминатор", "Боевик", "Скачать"));
        movieList.add(new Movie("Звездные войны", "Фантастика", "В кино"));
        movieList.add(new Movie("Достать ножи", "Комедия", "Скачать"));
        movieList.add(new Movie("Солнцестояние", "Ужасы", "Скачать"));
        recyclerAdapter = new RecyclerAdapter(this, movieList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.hasFixedSize();

        btnAdd.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnAccept.setOnClickListener(this);
        btnCancel.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.btn_search:
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.btn_accept:
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.btn_cancel:
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }
}
