package com.example.registrationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    private Button signin;
    private TextView signup;
    FirebaseAuth ifirebaseAuth;
    private FirebaseAuth.AuthStateListener iAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ifirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.pssedit);
        signin = findViewById(R.id.signupbtn);
        signup = findViewById(R.id.textView3);
        iAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser iFareBaseUser = ifirebaseAuth.getCurrentUser();
                if(iFareBaseUser != null) {
                    Toast.makeText(LoginActivity.this, "You are logged in !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "error occurd,try again !", Toast.LENGTH_SHORT).show();

                }
            }
        };

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId = email.getText().toString();
                String pwd = password.getText().toString();
                if (emailId.isEmpty()) {
                    email.setError("email is required ! ");
                    email.requestFocus();
                }
                else if(pwd.isEmpty()) {
                    email.setError("password is required ! ");
                    email.requestFocus();
                }
                else if (emailId.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "all fields are required !", Toast.LENGTH_SHORT).show();
                }
                else if (!(emailId.isEmpty() && pwd.isEmpty())) {
                    ifirebaseAuth.signInWithEmailAndPassword(emailId,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "sign up was unsuccessuful,please try again !", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this, "error occured !", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}