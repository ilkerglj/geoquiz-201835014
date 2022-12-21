package com.example.geoquiz201835014;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {

        Intent intent = new Intent(MainActivity.this, StartGame.class);
        startActivity(intent);

        finish();
    }
}