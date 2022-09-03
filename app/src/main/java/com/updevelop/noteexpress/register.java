package com.updevelop.noteexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    EditText username;
    EditText studentID;
    EditText password;
    EditText confirmPassword;
    Button signUp;
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.);
        studentID = findViewById(R.id.);
        password = findViewById(R.id.);
        confirmPassword = findViewById(R.id.);
        signUp = findViewById(R.id.);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });
    }
    public void checkData(){
        if (isEmpty(username)) {
            username.setError("Empty Username !! ");
            Toast.makeText(this, "Please Enter Your Username ! ", Toast.LENGTH_SHORT).show();
        }
        if (isEmpty(password)) {
            password.setError("Empty Password !! ");
            Toast.makeText(this, "Please Enter Your Password ! ", Toast.LENGTH_SHORT).show();
        }
        if (isEmpty(studentID)) {
            studentID.setError("Empty Student ID !! ");
            Toast.makeText(this, "Please Enter Your StudentID ! ", Toast.LENGTH_SHORT).show();
        }
        if (isEmpty(confirmPassword)) {
            confirmPassword.setError("Empty Password !! ");
            Toast.makeText(this, "Please Confirm Your Password ! ", Toast.LENGTH_SHORT).show();
        }
        if (!password.equals(confirmPassword)){
            Toast.makeText(this, "Password Doesn't Matches With Confirm Password", Toast.LENGTH_SHORT).show();
            confirmPassword.setError("?");
            password.setError("?");
        }
        else{
            String user_Info = username.getText().toString();
            String password_Info = password.getText().toString();
            String studentID_info = studentID.getText().toString();
            
//            setAuthData(user_Info , password_Info);
            storeDataBase(user_Info,password_Info,studentID_info);
            setAuthData(user_Info,password_Info);

        }


    }
    public void storeDataBase(String username ,String password , String studentID){
        this.db = FirebaseFirestore.getInstance();
        Map<String,Object> loginDeatils = new HashMap<>();
        loginDeatils.put("usernameList", username);
        loginDeatils.put("password",password);
        loginDeatils.put("studentID",studentID);

        db.collection("loginDetails").document(username).set(loginDeatils).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(register.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                setAuthData(username,password);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    private void setAuthData(String username , String password){
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(register.this, "AuthData Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}