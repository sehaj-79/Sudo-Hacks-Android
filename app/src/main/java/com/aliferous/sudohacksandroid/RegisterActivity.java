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
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    CheckBox CBRecovered, CBPositive, CBFree;

    FirebaseAuth auth;
    DatabaseReference reference;

    Button CreateAccountButton;
    EditText InputName, InputPhoneNumber, InputPassword, InputEmail, InputConfirmPass, InputLocality,  InputPin , InputAge;
    Spinner InputBlood, InputGender, InputCity, InputState;
    String txt_BloodGroup, txt_Gender, txt_DonorType, txt_State, txt_City;

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

        CBPositive = findViewById(R.id.register_CB_Positive);
        CBRecovered = findViewById(R.id.register_CB_Recovered);
        CBFree = findViewById(R.id.register_CB_Free);

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

        CBPositive.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txt_DonorType = "None";
                CBRecovered.setChecked(false);
                CBFree.setChecked(false);
                return false;
            }
        });

        CBRecovered.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txt_DonorType = "Plasma Donor";
                CBPositive.setChecked(false);
                CBFree.setChecked(false);
                return false;
            }
        });

        CBFree.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txt_DonorType = "Blood Donor";
                CBRecovered.setChecked(false);
                CBPositive.setChecked(false);
                return false;
            }
        });



        InputBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txt_BloodGroup = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        InputGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txt_Gender = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        InputState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txt_State = adapterView.getSelectedItem().toString();
                SetDistrictSpinner();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        InputCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txt_City = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = InputName.getText().toString();
                String txt_email = InputEmail.getText().toString();
                String txt_phone = InputPhoneNumber.getText().toString();
                String txt_password = InputPassword.getText().toString();
                String txt_cpassword = InputConfirmPass.getText().toString();
                String txt_Locality = InputLocality.getText().toString();
                String txt_Pincode = InputPin.getText().toString();
                String txt_Age = InputAge.getText().toString();



                if(TextUtils.isEmpty(txt_username)  ||  TextUtils.isEmpty(txt_password)  ||  TextUtils.isEmpty(txt_email)  ||  TextUtils.isEmpty(txt_phone)  ||  TextUtils.isEmpty(txt_Locality)   ||  TextUtils.isEmpty(txt_Pincode)  ||  TextUtils.isEmpty(txt_Age)  ){
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else if (txt_password.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password must be atleast 6 characters long", Toast.LENGTH_SHORT).show();
                }
                else if (!(txt_password.equals(txt_cpassword))){
                    Toast.makeText(RegisterActivity.this, "Please Recheck your Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    register(txt_username, txt_email, txt_password,txt_phone, txt_Age, txt_Locality, txt_City, txt_State, txt_Pincode, txt_BloodGroup, txt_Gender, txt_DonorType);
                }
            }
        });

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


    private void register(final String username, final String email, final String password, final String phone, final String age, final String locality, final String city, final String state, final String pincode, final String bloodgroup, final String gender, final String donorType) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("name", username);
                            hashMap.put("gender", gender);
                            hashMap.put("email", email);
                            hashMap.put("password", password);
                            hashMap.put("phone", password);
                            hashMap.put("blood", bloodgroup);
                            hashMap.put("locality", locality);
                            hashMap.put("city", city);
                            hashMap.put("state", state);
                            hashMap.put("pin", pincode);
                            hashMap.put("DonorType", donorType);

                            hashMap.put("search", username.toLowerCase());


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this,"Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Illegal Email Id or Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void SetDistrictSpinner(){

        ArrayAdapter<String> spinnerArrayAdapter;
        switch (txt_State){
            case "Select Your State" : spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_default_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Andhra Pradesh" :  spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_andhra_pradesh_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Arunachal Pradesh": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_arunachal_pradesh_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Assam": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_assam_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Bihar": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_bihar_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Chhattisgarh": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_chhattisgarh_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Goa": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_goa_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Gujarat": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_gujarat_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Haryana": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_haryana_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Himachal Pradesh": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_himachal_pradesh_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Jharkhand": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_jharkhand_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Karnataka": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_karnataka_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Kerala": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_kerala_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Madhya Pradesh": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_madhya_pradesh_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Maharashtra": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_maharashtra_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Manipur": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_manipur_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Meghalaya": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_meghalaya_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Mizoram": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_mizoram_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Nagaland": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_nagaland_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Odisha": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_odisha_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Punjab": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_punjab_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Rajasthan": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_rajasthan_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Sikkim": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_sikkim_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Tamil Nadu": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_tamil_nadu_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Telangana": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_telangana_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Tripura": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_tripura_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Uttar Pradesh": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_uttar_pradesh_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Uttarakhand": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_uttarakhand_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "West Bengal": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_west_bengal_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Andaman and Nicobar Islands": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_andaman_nicobar_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Chandigarh": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_chandigarh_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Dadra and Nagar Haveli": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_dadra_nagar_haveli_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Daman and Diu": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_daman_diu_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Delhi": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_delhi_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Jammu and Kashmir": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_jammu_kashmir_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Lakshadweep": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_lakshadweep_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Ladakh": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_ladakh_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            case "Puducherry": spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_puducherry_districts));
                InputCity.setAdapter(spinnerArrayAdapter);
                break;
            default:  break;
        }

    }

}