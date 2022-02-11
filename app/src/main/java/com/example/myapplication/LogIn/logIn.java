package com.example.myapplication.LogIn;

import android.content.Intent;
import android.content.res.ColorStateList;
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
import com.example.myapplication.mainPage.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

public class logIn extends AppCompatActivity {

    //vars
    EditText usernameEdit;
    EditText passwordEdit;
    Button logButton;
    TextView regText;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        usernameEdit = (EditText) findViewById(R.id.logIn_username);
        passwordEdit = (EditText) findViewById(R.id.logIn_password);
        logButton = (Button) findViewById(R.id.logIn_btn);
        regText = (TextView) findViewById(R.id.logIn_reg_word);

        DatabaseHelper db = new DatabaseHelper(this);
        logButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (usernameEdit.getText() == null)
                        {
                            TextInputLayout user =  findViewById(R.id.logIn_username);
                            user.setError("Enter username");
                        }
                        else if (passwordEdit.getText() == null)
                        {
                            TextInputLayout pass =  findViewById(R.id.logIn_password);
                            pass.setError("Enter password");
                        }
                        else {
                            String username = usernameEdit.getText().toString();
                            String password = passwordEdit.getText().toString();
                            boolean valid = db.checkLogIn(username, password);
                            if (valid)
                            {
                                Intent myintent = new Intent(view.getContext(), MainActivity.class);
                                startActivity(myintent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),
                                        "Error enter correct data",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
        regText.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myintent = new Intent(view.getContext(), Register.class);
                        startActivity(myintent);
                    }
                }
        );

    }
}
