package com.aliferous.sudohacksandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    Button Save;
    EditText Phone,Age;
    CheckBox Positive,Recovered,Free;

    String txt_Phone, txt_Age, txt_DonorType;

    FirebaseUser firebaseUser;
    DatabaseReference dbr;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Save = findViewById(R.id.profile_save);
        Phone = findViewById(R.id.profile_Phone);
        Age = findViewById(R.id.profile_Age);
        Positive = findViewById(R.id.profile_CB_Positive);
        Recovered = findViewById(R.id.profile_CB_Recovered);
        Free = findViewById(R.id.profile_CB_Free);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = firebaseUser.getUid();
        dbr = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txt_Age = snapshot.child("age").getValue().toString();
                txt_Phone = snapshot.child("phone").getValue().toString();
                txt_DonorType = snapshot.child("DonorType").getValue().toString();

                Phone.setText(txt_Phone);
                Age.setText(txt_Age);

                if (txt_DonorType.equals("None")){
                    Positive.setChecked(true);
                    Recovered.setChecked(false);
                    Free.setChecked(false);
                }

                else if (txt_DonorType.equals("Blood Donor")){
                    Positive.setChecked(false);
                    Recovered.setChecked(false);
                    Free.setChecked(true);
                }

                else if (txt_DonorType.equals("Plasma Donor")){
                    Positive.setChecked(false);
                    Recovered.setChecked(true);
                    Free.setChecked(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Positive.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txt_DonorType = "None";
                Recovered.setChecked(false);
                Free.setChecked(false);
                return false;
            }
        });

        Recovered.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txt_DonorType = "Plasma Donor";
                Positive.setChecked(false);
                Free.setChecked(false);
                return false;
            }
        });

        Free.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txt_DonorType = "Blood Donor";
                Recovered.setChecked(false);
                Positive.setChecked(false);
                return false;
            }
        });


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_Age = Age.getText().toString();
                txt_Phone = Phone.getText().toString();

                dbr.child("age").setValue(txt_Age);
                dbr.child("phone").setValue(txt_Phone);
                dbr.child("DonorType").setValue(txt_DonorType);

                Toast.makeText(ProfileActivity.this, "All Changes Have Been Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });


    }
}