package com.example.project_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    Button SignBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        loginBtn = findViewById(R.id.login);
        SignBtn = findViewById(R.id.signup);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginScreen = new Intent(getApplicationContext(), Login.class);
                startActivity(loginScreen);
                SignBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent SignScreen = new Intent(getApplicationContext(),Signup.class);
                        startActivity(SignScreen);
                    }

                });
            }
        });
    }
}