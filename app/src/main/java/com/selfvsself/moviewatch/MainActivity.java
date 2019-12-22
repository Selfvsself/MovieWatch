package com.selfvsself.moviewatch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = findViewById(R.id.button);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);

                final EditText et_username = bottomSheetDialog.findViewById(R.id.et_username);
                final EditText et_pass = bottomSheetDialog.findViewById(R.id.et_pass);
                Button btn_submit = bottomSheetDialog.findViewById(R.id.button2);

                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et_username.getText().toString().equals("admin") &&
                        et_pass.getText().toString().equals("admin")) {
                            final AlertDialog.Builder bulder = new AlertDialog.Builder(v.getContext());
                            bulder.setTitle("Login Succesful");
                            bulder.setMessage("Welcome to Android");

                            bulder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = bulder.create();
                            alertDialog.show();
                        } else {
                            Toast.makeText(v.getContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                bottomSheetDialog.show();
            }
        });
    }
}
