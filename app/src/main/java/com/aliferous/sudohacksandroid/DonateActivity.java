package com.aliferous.sudohacksandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DonateActivity extends AppCompatActivity {

    ImageView donate,profile,feedback,chats;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DonateActivity.this, HomeActivity.class);
        startActivity(intent);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        
        donate = findViewById(R.id.circle1);
        profile = findViewById(R.id.circle2);
        chats = findViewById(R.id.circle3);
        feedback = findViewById(R.id.circle4);

        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonateActivity.this, ChatsActivity.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonateActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonateActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonateActivity.this, DonateActivity.class);
                startActivity(intent);
            }
        });
    }
}