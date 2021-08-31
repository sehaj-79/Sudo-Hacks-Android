package com.aliferous.sudohacksandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackActivity extends AppCompatActivity {

    Button Submit;
    EditText Message;

    FirebaseUser firebaseUser;
    DatabaseReference dbr, reference,feedbackRef;
    String Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        reference = FirebaseDatabase.getInstance().getReference("Feedback");

        Submit = findViewById(R.id.feedbackSubmit);
        Message = findViewById(R.id.feebackMessage);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = firebaseUser.getUid();
        dbr = FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("name");

        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Name = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_Message = Message.getText().toString();
                feedbackRef = reference.push();
                feedbackRef.child("Message").setValue(txt_Message);
                feedbackRef.child("User").setValue(Name);
                Toast.makeText(FeedbackActivity.this, "Thank You For Your Valuable Feedback", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FeedbackActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}