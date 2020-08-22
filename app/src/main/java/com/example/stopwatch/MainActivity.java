package com.example.stopwatch;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startbutton , reset;
    Handler handler;

    private Button stopbutton;
    Canvasview c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new Handler();
        c = findViewById(R.id.c);
        reset = findViewById(R.id.button3);
        startbutton=findViewById(R.id.button);
        stopbutton=findViewById(R.id.button2);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Canvasview.drawing = true;
                Canvasview.p = true;
                c.postInvalidate();
                reset.setEnabled(false);
                startbutton.setEnabled(false);
            }
        });
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                c.invalidate();
                Canvasview.drawing = false;
                Canvasview.p = false;
                Canvasview.first=true;
                reset.setEnabled(true);
                startbutton.setEnabled(true);


            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Canvasview.mSecond = 0;
                Canvasview.mMinute = 0;
                Canvasview.time = 0;
                c.invalidate();
                Canvasview.drawing = false;
                Canvasview.p = false;
                Canvasview.first=true;
//                c.postInvalidate();
            }
        });
    }
}