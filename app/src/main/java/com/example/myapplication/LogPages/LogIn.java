package com.example.myapplication.LogPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.mainPage.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LogIn extends AppCompatActivity {

    //vars
    private TextInputLayout usernameEdit;
    private TextInputLayout passwordEdit;
    private Button logButton;
    private TextView regText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        usernameEdit = (TextInputLayout) findViewById(R.id.logIn_username);
        passwordEdit = (TextInputLayout) findViewById(R.id.logIn_password);
        logButton = (Button) findViewById(R.id.logIn_btn);
        regText = (TextView) findViewById(R.id.logIn_reg_word);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        logButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (usernameEdit.getEditText().getText() == null) {


                            usernameEdit.setError("Enter username");


                        } else if (passwordEdit.getEditText().getText() == null) {


                            passwordEdit.setError("Enter password");


                        } else {
                            String username = usernameEdit.getEditText().getText().toString();
                            String password = passwordEdit.getEditText().getText().toString();
                            boolean valid = db.checkLogIn(username, password);
                            if (valid) {
                                Intent myintent = new Intent(view.getContext(), MainActivity.class);
                                startActivity(myintent);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Error enter correct data",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );




    }
    public void logIn_go_Reg(View view) {
        Intent myintent = new Intent(this, Register.class);
        startActivity(myintent);}
}
