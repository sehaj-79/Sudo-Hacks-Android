package com.aliferous.sudohacksandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    Button logout,donate,profile,chats,feedback,plasma_donation,blood_donation;
    String txt;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        /*logout = findViewById(R.id.home_logout);
        donate = findViewById(R.id.home_donate);
        profile = findViewById(R.id.home_profile);
        chats = findViewById(R.id.home_chats);
        feedback = findViewById(R.id.home_feedback);
        plasma_donation = findViewById(R.id.home_plasma_donation);
        blood_donation = findViewById(R.id.home_blood_donation);*/
//
//
//        blood_donation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, BloodActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        plasma_donation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, PlasmaActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        donate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, DonateActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, FeedbackActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        chats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, ChatsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Paper.book().destroy();
//
//                Intent intent = new Intent(HomeActivity.this, StartActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//            }
//        });


    }
}