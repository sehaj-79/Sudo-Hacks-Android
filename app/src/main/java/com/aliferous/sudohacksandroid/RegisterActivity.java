package com.aliferous.sudohacksandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    Button Next1,Next2,Next3,Next4,Next5;
    ConstraintLayout Page1,Page2,Page3,Page4,Page5;
    TextView SignIn;

    FirebaseAuth auth;
    DatabaseReference reference;

    private Button CreateAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword, InputEmail, InputConfirmPass, InputLocality, InputState,InputCity, InputPin , InputAge;
    private Spinner InputBlood, InputGender, CovidStatus;
    private ProgressDialog loadingBar;

    private String parentDbName = "Users", acctype= "Recipient";
    private TextView DocLink, NotDocLink;

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

        SignIn = findViewById(R.id.register_SignIn);

        Page1 = findViewById(R.id.register_page1);
        Page2 = findViewById(R.id.register_page2);
        Page3 = findViewById(R.id.register_page3);
        Page4 = findViewById(R.id.register_page4);
        Page5 = findViewById(R.id.register_page5);

        auth = FirebaseAuth.getInstance();

        CreateAccountButton = findViewById(R.id.page5_next);
        InputName = findViewById(R.id.register_FullName);
        InputPassword = findViewById(R.id.register_Password);
        InputPhoneNumber = findViewById(R.id.register_Phone);
        InputEmail = findViewById(R.id.register_Email);
        InputBlood = findViewById(R.id.register_BloodGroup);
        InputConfirmPass = findViewById(R.id.register_CPassword);
        InputLocality = findViewById(R.id.register_Locality);
        InputState = findViewById(R.id.register_State);
        InputCity = findViewById(R.id.register_City);
        InputPin = findViewById(R.id.register_Pincode);
        InputAge = findViewById(R.id.register_age);
        InputGender = findViewById(R.id.register_Gender);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


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
        Page2.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(Page1, "translationX", 0f, -1200f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(Page2, "translationX", 1200f, 0f);
        oa1.setDuration(250);
        oa2.setDuration(250);
        oa1.setInterpolator(new AccelerateDecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Page1.setVisibility(View.GONE);
            }
        });
        oa1.start();
        oa2.start();
    }
    public void Page2_Next(){
        Page3.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(Page2, "translationX", 0f, -1200f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(Page3, "translationX", 1200f, 0f);
        oa1.setDuration(250);
        oa2.setDuration(250);
        oa1.setInterpolator(new AccelerateDecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Page2.setVisibility(View.GONE);
            }
        });
        oa1.start();
        oa2.start();
    }
    public void Page3_Next(){
        Page4.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(Page3, "translationX", 0f, -1200f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(Page4, "translationX", 1200f, 0f);
        oa1.setDuration(250);
        oa2.setDuration(250);
        oa1.setInterpolator(new AccelerateDecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Page3.setVisibility(View.GONE);
            }
        });
        oa1.start();
        oa2.start();
    }
    public void Page4_Next(){
        Page5.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(Page4, "translationX", 0f, -1200f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(Page5, "translationX", 1200f, 0f);
        oa1.setDuration(250);
        oa2.setDuration(250);
        oa1.setInterpolator(new AccelerateDecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Page4.setVisibility(View.GONE);
            }
        });
        oa1.start();
        oa2.start();
    }

    public void Page2_Back(){
        Page1.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(Page1, "translationX", -1200f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(Page2, "translationX", 0f, 1200f);
        oa1.setDuration(250);
        oa2.setDuration(250);
        oa1.setInterpolator(new AccelerateDecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Page2.setVisibility(View.GONE);
            }
        });
        oa1.start();
        oa2.start();
    }
    public void Page3_Back(){
        Page2.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(Page2, "translationX", -1200f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(Page3, "translationX", 0f, 1200f);
        oa1.setDuration(250);
        oa2.setDuration(250);
        oa1.setInterpolator(new AccelerateDecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Page3.setVisibility(View.GONE);
            }
        });
        oa1.start();
        oa2.start();
    }
    public void Page4_Back(){
        Page3.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(Page3, "translationX", -1200f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(Page4, "translationX", 0f, 1200f);
        oa1.setDuration(250);
        oa2.setDuration(250);
        oa1.setInterpolator(new AccelerateDecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Page4.setVisibility(View.GONE);
            }
        });
        oa1.start();
        oa2.start();
    }
    public void Page5_Back(){
        Page4.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(Page4, "translationX", -1200f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(Page5, "translationX", 0f, 1200f);
        oa1.setDuration(250);
        oa2.setDuration(250);
        oa1.setInterpolator(new AccelerateDecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Page5.setVisibility(View.GONE);
            }
        });
        oa1.start();
        oa2.start();
    }

    private void CreateAccount()
    {
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
        String confpassword = InputConfirmPass.getText().toString();
        String email = InputEmail.getText().toString();
        String blood = InputBlood.getText().toString();
        String house = InputHouse.getText().toString();
        String locality = InputLocality.getText().toString();
        String district = InputDistrict.getText().toString();
        String state = InputState.getText().toString();
        String pin = InputPin.getText().toString();
        String country = "India";
        String  Positive = "false";
        String Free = "false";
        String Recovered= "false";
        String Age = InputAge.getText().toString();
        String Gender = InputGender.getText().toString();

        if (covidpositive.isChecked())
        {
            Positive = "true";
        }
        if (covidfree.isChecked())
        {
            Free = "true";
        }
        if (covidrecoverd.isChecked())
        {
            Recovered = "true";
        }


        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(blood))
        {
            Toast.makeText(this, "Please write your blood type...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(confpassword))
        {
            Toast.makeText(this, "Please confirm your password...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(house))
        {
            Toast.makeText(this, "Please write your complete address...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(locality))
        {
            Toast.makeText(this, "Please write your complete address...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(district))
        {
            Toast.makeText(this, "Please write your complete address...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(state))
        {
            Toast.makeText(this, "Please write your complete address...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pin))
        {
            Toast.makeText(this, "Please write your pincode...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Gender))
        {
            Toast.makeText(this, "Please write your gender...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Age))
        {
            Toast.makeText(this, "Please write your age...", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(confpassword))
        {
            Toast.makeText(this, "Passwords don't match...", Toast.LENGTH_SHORT).show();
        }
        else if (x==0)
        {
            Toast.makeText(this, "Please complete covid related information...", Toast.LENGTH_SHORT).show();
        }
        else if (x==2&&Positive.equals("true"))
        {
            Toast.makeText(this, "You can't be a donor because you are covid positive...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(name, phone, password , email , blood, house, locality , district, state, pin , country, acctype, Positive,Free, Recovered,Gender,Age);
        }
    }

    private void ValidatephoneNumber(final String name, final String phone, final String password, final String email, final String blood, final String house, final String locality, final String district, final String state, final String pin, final String country, final String accType, final String positive, final String free, final String recovered, final String gender,final String age)
    {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            final String userid = firebaseUser.getUid();

                            final DatabaseReference RootRef;
                            RootRef = FirebaseDatabase.getInstance().getReference();

                            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {

                                    if (!(dataSnapshot.child(parentDbName).child(userid).exists())) {
                                        HashMap<String, Object> userdataMap = new HashMap<>();
                                        userdataMap.put("id", userid);
                                        userdataMap.put("phone", phone);
                                        userdataMap.put("name", name);
                                        userdataMap.put("password", password);
                                        userdataMap.put("email", email);
                                        userdataMap.put("blood", blood);
                                        userdataMap.put("house", house);
                                        userdataMap.put("locality", locality);
                                        userdataMap.put("district", district);
                                        userdataMap.put("state", state);
                                        userdataMap.put("country", country);
                                        userdataMap.put("pin", pin);
                                        userdataMap.put("accType", accType);
                                        userdataMap.put("CovidPositive", positive);
                                        userdataMap.put("CovidFree", free);
                                        userdataMap.put("CovidRecovered", recovered);
                                        userdataMap.put("gender", gender);
                                        userdataMap.put("age", age);
                                        userdataMap.put("imageURL", "default");
                                        userdataMap.put("status", "offline");


                                        RootRef.child(parentDbName).child(userid).updateChildren(userdataMap)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@androidx.annotation.NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                                            loadingBar.dismiss();

                                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                            startActivity(intent);
                                                        } else {
                                                            loadingBar.dismiss();
                                                            Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                        /*if (!(dataSnapshot.child(parentDbName).child(phone).exists())) {
                                            HashMap<String, Object> userdataMap1 = new HashMap<>();
                                            userdataMap1.put("id", userid);
                                            userdataMap1.put("phone", phone);
                                            userdataMap1.put("name", name);
                                            userdataMap1.put("password", password);
                                            userdataMap1.put("email", email);
                                            userdataMap1.put("blood", blood);
                                            userdataMap1.put("house", house);
                                            userdataMap1.put("locality", locality);
                                            userdataMap1.put("district", district);
                                            userdataMap1.put("state", state);
                                            userdataMap1.put("country", country);
                                            userdataMap1.put("pin", pin);
                                            userdataMap1.put("accType", accType);
                                            userdataMap1.put("CovidPositive", positive);
                                            userdataMap1.put("CovidFree", free);
                                            userdataMap1.put("CovidRecovered", recovered);
                                            userdataMap1.put("gender", gender);
                                            userdataMap1.put("age", age);
                                            userdataMap1.put("imageURL", "default");
                                            userdataMap1.put("status", "offline");


                                            RootRef.child(parentDbName).child(phone).updateChildren(userdataMap)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@androidx.annotation.NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                                                loadingBar.dismiss();

                                                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                                startActivity(intent);
                                                            } else {
                                                                loadingBar.dismiss();
                                                                Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        }*/

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "This " + email + " already exists.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                });
    }

}