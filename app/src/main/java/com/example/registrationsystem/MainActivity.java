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

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    private Button signup;
    private TextView signin;
    FirebaseAuth ifirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ifirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.pssedit);
        signin = findViewById(R.id.textView3);
        signup = findViewById(R.id.signupbtn);


        signup.setOnClickListener(new View.OnClickListener() {
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
                 Toast.makeText(MainActivity.this, "all fields are required !", Toast.LENGTH_SHORT).show();
             }
             else if (!(emailId.isEmpty() && pwd.isEmpty())) {
                 ifirebaseAuth.createUserWithEmailAndPassword(emailId,pwd)
                         .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (!task.isSuccessful()){
                             Toast.makeText(MainActivity.this, "sign up was unsuccessuful,please try again !", Toast.LENGTH_SHORT).show();
                         } else {
                             startActivity(new Intent(MainActivity.this,HomeActivity.class));

                         }
                     }
                 });
             }
             else{
                 Toast.makeText(MainActivity.this, "error occured !", Toast.LENGTH_SHORT).show();
             }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}