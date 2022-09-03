package com.updevelop.noteexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.io.FileReader;
import java.util.Map;

public class loginPage extends AppCompatActivity {
    EditText userName;
    EditText password;
    Button loginBtn;
    TextView signUp;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        VideoView videoview = (VideoView) findViewById(R.id.videoview);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.anime_login);
        videoview.setVideoURI(uri);
        videoview.start();
        FirebaseAuth.getInstance();
        if (FirebaseAuth.getInstance().getCurrentUser()!= null){
            startActivity(new Intent(loginPage.this, homePage.class));

        }
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);
        signUp = findViewById(R.id.signUP);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });


    }
    public void checkData(){
        if (isEmpty(userName)) {
            userName.setError("Empty Username !! ");
            Toast.makeText(this, "Please Enter Your Username ! ", Toast.LENGTH_SHORT).show();
        }
        if (isEmpty(password)) {
            password.setError("Empty Password !! ");
            Toast.makeText(this, "Please Enter Your Password ! ", Toast.LENGTH_SHORT).show();
        }

        else{
            String user_Info = userName.getText().toString();
            String password_Info = password.getText().toString();
            checkUserNameData(user_Info , password_Info);

        }

    }

    public void checkUserNameData(String userName , String password){
        this.db = FirebaseFirestore.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        db.collection("loginDetails").document(userName).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    Map<String,Object> checkData = task.getResult().getData();
                    assert checkData != null;
                    String usernameCheckData = String.valueOf(checkData.get("username"));
                    String passwordCheckData = String.valueOf(checkData.get("password"));
                    if (usernameCheckData.equals(userName) && passwordCheckData.equals(password)){
                        startActivity(new Intent(loginPage.this, homePage.class));
                    }
                    else{
                        Toast.makeText(loginPage.this, "Invalid Username / Password", Toast.LENGTH_SHORT).show();
                    }

                }
                else Toast.makeText(loginPage.this, "Error fetching data from Database", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    }
