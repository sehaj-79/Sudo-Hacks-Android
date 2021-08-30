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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import static java.security.AccessController.getContext;

public class BloodActivity extends AppCompatActivity {

    TextView locality,pincode,gender,age,bloodgroup,test;

    DatabaseReference reference;

    private RecyclerView recyclerView;

    private BloodDonorAdapter bloodDonorAdapter;
    private List<User> mUsers;

    FirebaseUser firebaseUser;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        recyclerView = findViewById(R.id.blood_donor_recycler_view);
        test = findViewById(R.id.text_test);

        /*locality = findViewById(R.id.locality);
        pincode = findViewById(R.id.pincode);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        bloodgroup = findViewById(R.id.bloodgroup);*/

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                assert user != null;
                test.setText(user.getLocality());
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

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);

                        if (user != null && user.getId() != null) {
                            assert firebaseUser != null;
                            if (!user.getId().equals(firebaseUser.getUid())) {
                                mUsers.add(user);
                            }
                        }

                    }

                    bloodDonorAdapter = new BloodDonorAdapter(BloodActivity.this, mUsers, false);
                    recyclerView.setAdapter(bloodDonorAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}