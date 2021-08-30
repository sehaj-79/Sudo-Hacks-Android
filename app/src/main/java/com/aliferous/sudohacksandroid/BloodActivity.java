package com.aliferous.sudohacksandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.BloodDonorAdapter;
import Model.User;

public class BloodActivity extends AppCompatActivity {

    TextView locality,pincode,gender,age,bloodgroup;

    DatabaseReference reference;

    private RecyclerView recyclerView;

    private BloodDonorAdapter userAdapter;
    private List<User> mUsers;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);



        locality = findViewById(R.id.locality);
        recyclerView = findViewById(R.id.blood_donor_recycler_view);
        pincode = findViewById(R.id.pincode);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        bloodgroup = findViewById(R.id.bloodgroup);


        reference = FirebaseDatabase.getInstance().getReference("Users");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                assert user != null;
                locality.setText(user.getLocality());
                pincode.setText(user.getPincode());
                gender.setText(user.getGender());
                age.setText(user.getAge());
                bloodgroup.setText(user.getBloodgroup());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(BloodActivity.this));

        mUsers = new ArrayList<>();

        readUsers();


    }


    private void readUsers() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);


                        if (user!=null && user.getUsername() != null ) {
                            mUsers.add(user);
                        }


                    }

                    userAdapter = new BloodDonorAdapter(BloodActivity.this, mUsers, false);
                    recyclerView.setAdapter(userAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}