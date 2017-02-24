package com.example.apache.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername;
    EditText edPassword;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = (EditText)findViewById(R.id.editUsername);
        edPassword = (EditText)findViewById(R.id.editPassword);

        Login = (Button)findViewById(R.id.buttonLogin);
    }

    public void login(View view){
        if(edUsername.getText().toString().equals("admin") && edPassword.getText().toString().equals("admin")){
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Wrong password! Repeat, please", Toast.LENGTH_SHORT).show();
            Login.setEnabled(false);
        }
    }

}
