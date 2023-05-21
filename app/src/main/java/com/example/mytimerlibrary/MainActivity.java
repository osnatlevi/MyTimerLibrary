package com.example.mytimerlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.timerlibrary.Timer;

public class MainActivity extends AppCompatActivity {


    private Button BTN_Show_Timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        initButtons();
    }

    private void initButtons() {

        BTN_Show_Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Timer.class);
                startActivity(intent);

            }
        });
    }


    private void findViews() {
        BTN_Show_Timer = findViewById(R.id.BTN_Show_Timer);

    }

}