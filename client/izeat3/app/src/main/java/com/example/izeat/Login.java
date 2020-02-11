package com.example.izeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends AppCompatActivity {

    private TextView welcome;
    private TextView conection;
    private EditText mail;
    private EditText password;
    private Button signup;
    private Button passwordforgotten;
    private Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        welcome=(TextView) findViewById(R.id.activity_main_welcome_txt);
        conection=(TextView) findViewById(R.id.activity_main_conection_txt);
        mail=(EditText) findViewById(R.id.activity_main_mail_input);
        password=(EditText) findViewById(R.id.activity_main_password_input);
        signup=(Button) findViewById(R.id.activity_main_signup_btn);
        passwordforgotten=(Button) findViewById(R.id.activity_passwordforgotten_btn);
        next=(Button) findViewById(R.id.activity_main_next_btn);
    }

}
