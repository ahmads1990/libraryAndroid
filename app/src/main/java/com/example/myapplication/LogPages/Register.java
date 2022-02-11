package com.example.myapplication.LogPages;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    //vars
    EditText usernameEdit;
    EditText passwordEdit;
    EditText emailEdit;
    Button regButton;
    TextView logText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.register);

        usernameEdit = (EditText) findViewById(R.id.logIn_password);
        passwordEdit = (EditText) findViewById(R.id.reg_password);
        emailEdit = (EditText) findViewById(R.id.reg_email);
        regButton = (Button) findViewById(R.id.reg_btn);
        logText = (TextView) findViewById(R.id.reg_go_login_btn);

        DatabaseHelper db = new DatabaseHelper(this);
        regButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (usernameEdit.getText() == null) {
                            TextInputLayout user = findViewById(R.id.reg_username);
                            user.setError("Enter username");
                        } else if (passwordEdit.getText() == null) {
                            TextInputLayout pass = findViewById(R.id.reg_password);
                            pass.setError("Enter password");
                        } else if (emailEdit.getText() == null) {
                            TextInputLayout email = findViewById(R.id.reg_email);
                            email.setError("Enter email");
                        } else {
                            String username = usernameEdit.getText().toString();
                            String password = passwordEdit.getText().toString();
                            String email = emailEdit.getText().toString();
                            boolean valid = db.addNewUser(username, password, email);
                            if (valid) {
                                Intent myintent = new Intent(view.getContext(), LogIn.class);
                                startActivity(myintent);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Error user already exists",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );

        logText.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myintent = new Intent(view.getContext(), LogIn.class);
                        startActivity(myintent);
                    }
                }
        );
    }
}
