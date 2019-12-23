package com.selfvsself.moviewatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior mbottomSheetBehavior;
    private TextView textViewState;
    private Button btnExpand, btnCollapse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mbottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        textViewState = findViewById(R.id.text_state);
        btnExpand = findViewById(R.id.btn_expand);
        btnCollapse = findViewById(R.id.btn_collapse);

        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            }
        });

        btnCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }
        });

        mbottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                textViewState.setText(String.valueOf(i));
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                textViewState.setText(String.valueOf(v));
            }
        });

    }
}
