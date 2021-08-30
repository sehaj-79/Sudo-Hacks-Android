package com.aliferous.sudohacksandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button Next1,Next2,Next3,Next4,Next5;
    ConstraintLayout Page1,Page2,Page3,Page4,Page5;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Next1 = findViewById(R.id.page1_next);
        Next2 = findViewById(R.id.page2_next);
        Next3 = findViewById(R.id.page3_next);
        Next4 = findViewById(R.id.page4_next);
        Next5 = findViewById(R.id.page5_next);

        Page1 = findViewById(R.id.register_page1);
        Page2 = findViewById(R.id.register_page2);
        Page3 = findViewById(R.id.register_page3);
        Page4 = findViewById(R.id.register_page4);
        Page5 = findViewById(R.id.register_page5);


        Page1.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Page1_Next();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
            }
        });

        Page2.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Page2_Next();

            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Page2_Back();
            }
        });

        Page3.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Page3_Next();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Page3_Back();
            }
        });

        Page4.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Page4_Next();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Page4_Back();
            }
        });

        Page5.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Page5_Back();
            }
        });


        Next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page1_Next();
            }
        });

        Next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page2_Next();
            }
        });

        Next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page3_Next();
            }
        });

        Next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page4_Next();
            }
        });

    }

    public void Page1_Next(){
        Page1.setVisibility(View.GONE);
        Page2.setVisibility(View.VISIBLE);
    }
    public void Page2_Next(){
        Page2.setVisibility(View.GONE);
        Page3.setVisibility(View.VISIBLE);
    }
    public void Page3_Next(){
        Page3.setVisibility(View.GONE);
        Page4.setVisibility(View.VISIBLE);
    }
    public void Page4_Next(){
        Page4.setVisibility(View.GONE);
        Page5.setVisibility(View.VISIBLE);
    }

    public void Page2_Back(){
        Page2.setVisibility(View.GONE);
        Page1.setVisibility(View.VISIBLE);
    }
    public void Page3_Back(){
        Page3.setVisibility(View.GONE);
        Page2.setVisibility(View.VISIBLE);
    }
    public void Page4_Back(){
        Page4.setVisibility(View.GONE);
        Page3.setVisibility(View.VISIBLE);
    }
    public void Page5_Back(){
        Page5.setVisibility(View.GONE);
        Page4.setVisibility(View.VISIBLE);
    }

}