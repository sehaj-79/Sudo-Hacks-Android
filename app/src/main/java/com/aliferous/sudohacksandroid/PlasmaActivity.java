package com.aliferous.sudohacksandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.BloodDonorAdapter;
import Adapter.PlasmaDonorAdapter;
import Model.User;

public class PlasmaActivity extends AppCompatActivity {

    TextView locality,pincode,gender,age,bloodgroup;

    DatabaseReference reference;

    private RecyclerView recyclerView;

    private PlasmaDonorAdapter plasmaDonorAdapter;
    private List<User> mUsers;

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plasma);

        recyclerView = findViewById(R.id.plasma_donor_recycler_view);
        locality = findViewById(R.id.plasma_donor_location);

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
                locality.setText("Your Location: "+user.getLocality());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

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
                        if (!user.getId().equals(firebaseUser.getUid()) && user.getDonorType().equals("Plasma Donor")) {
                            mUsers.add(user);
                        }
                    }

                }

                plasmaDonorAdapter = new PlasmaDonorAdapter(PlasmaActivity.this, mUsers, false);
                recyclerView.setAdapter(plasmaDonorAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}