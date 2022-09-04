package com.updevelop.noteexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class homePage extends AppCompatActivity {
    TextView usernameUpdate;
    ImageView dropDown;
    TextView fakeSpinner;
    ImageView history;
    ImageView dsa;
    ImageView chemistry;
    ImageView ecl;
    ImageView english;
    ImageView it;
    ImageView physical;
    ImageView python;
    String ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        usernameUpdate = findViewById(R.id.textView2);
        fakeSpinner = findViewById(R.id.fakeSpinner);
        dropDown = findViewById(R.id.dropDown);
        history = findViewById(R.id.history);
        dsa = findViewById(R.id.dsa);
        chemistry = findViewById(R.id.chemistry);
        ecl = findViewById(R.id.ecl);
        english = findViewById(R.id.english);
        it = findViewById(R.id.IT);
        physical = findViewById(R.id.physical);
        python = findViewById(R.id.python);
        String username = getIntent().getStringExtra("username");
        usernameUpdate.setText(" Hi , " + username);
        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fakeSpinner.setText("Public Class Notes");
            }
        });

        if (fakeSpinner.getText().toString().equals("Public Class Notes")){
            ans = "public";
        }
        else ans = "private";


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this , history.class);
                intent.putExtra("ans", ans);
                startActivity(intent);
            }
        });
        dsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homePage.this,dsa.class));
            }
        });
//        ecl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(homePage.this,ecl.class));
//            }
//        });english.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(homePage.this,english.class));
//            }
//        });it.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(homePage.this,it.class));
//            }
//        });physical.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(homePage.this,physical.class));
//            }
//        });python.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(homePage.this,python.class));
//            }
//        });
//









    }

}