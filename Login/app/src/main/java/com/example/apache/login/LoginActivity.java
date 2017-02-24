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

    public static int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = (EditText)findViewById(R.id.editUsername);
        edPassword = (EditText)findViewById(R.id.editPassword);

        Login = (Button)findViewById(R.id.buttonLogin);

        attempts = 5;
    }

    public void login(View view) {
        if (edUsername.getText().toString().equals("admin") && edPassword.getText().toString().equals("admin")) {
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
        else {
            if (editdist("admin", edPassword.getText().toString()) == 1) {
                attempts--;
                Toast.makeText(getApplicationContext(), "You have " + attempts + " attempts! Repeat, please", Toast.LENGTH_SHORT).show();
                if (attempts == 0) {
                    this.finish();
                }
            }
            if (editdist("admin", edPassword.getText().toString()) > 1) {
                int second_attempts = attempts - 3;
                Toast.makeText(getApplicationContext(), "You have " + second_attempts + " attempts! Repeat, please", Toast.LENGTH_SHORT).show();
                attempts--;
                if (attempts <= 2) {
                    this.finish();
                }
            }
        }
    }

    int editdist(String S1, String S2) {
        int m = S1.length(), n = S2.length();
        int[] D1;
        int[] D2 = new int[n + 1];

        for(int i = 0; i <= n; i ++)
            D2[i] = i;

        for(int i = 1; i <= m; i ++) {
            D1 = D2;
            D2 = new int[n + 1];
            for(int j = 0; j <= n; j ++) {
                if(j == 0) D2[j] = i;
                else {
                    int cost = (S1.charAt(i - 1) != S2.charAt(j - 1)) ? 1 : 0;
                    if(D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
                        D2[j] = D2[j - 1] + 1;
                    else if(D1[j] < D1[j - 1] + cost)
                        D2[j] = D1[j] + 1;
                    else
                        D2[j] = D1[j - 1] + cost;
                }
            }
        }
        return D2[n];
    }

}
