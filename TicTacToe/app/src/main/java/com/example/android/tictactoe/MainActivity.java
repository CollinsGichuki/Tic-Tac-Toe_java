package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This is where/how the points will be displayed
        setContentView(R.layout.activity_main);
        Button button_1 = (Button) findViewById(R.id.multiplayer_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        Button button_2 = (Button) findViewById(R.id.multiplayer_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }
    public void openActivity2(){
        Intent a = new Intent(this, Activity2.class);
        startActivity(a);
    }
    public void openActivity3(){
        Intent b = new Intent(this, Activity3.class);
        startActivity(b);
    }
}

