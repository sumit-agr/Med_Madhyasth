package com.cvrce.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    private Button mBtn;
    private EditText mdisplay;
    private EditText memail;
    private EditText mpass;
    private EditText mphone;

   public String displayName,email,pass,bloodgrp,phone;



    private FirebaseAuth mAuth;
    String[] bloodGroup = {"N.A","O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mdisplay =  (EditText)findViewById(R.id.reg_display);
        memail = (EditText) findViewById(R.id.reg_email);
        mpass = (EditText) findViewById(R.id.reg_pass);
        mphone = (EditText) findViewById(R.id.reg_phn);
        //bg = findViewById(R.id.spinner);


        databaseReference = database.getInstance().getReference("Prof");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        mBtn = (Button) findViewById(R.id.reg_btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayName = mdisplay.getText().toString();
                email = memail.getText().toString();
                pass = mpass.getText().toString();
                phone = mphone.getText().toString();

                //String bloodgrp  = bg.toString();

                if(!TextUtils.isEmpty(displayName)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(pass))
                {
                    register_user(displayName,email,pass);
                }

            }
        });
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodGroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        if(position>0) {
            Toast.makeText(getApplicationContext(), "Selected BloodGroup is : " + bloodGroup[position], Toast.LENGTH_LONG).show();
            switch(position)
            {
                case 1:
                    bloodgrp = "O+";
                    break;
                case 2:
                    bloodgrp = "O-";
                    break;
                case 3:
                    bloodgrp = "A+";
                    break;
                case 4:
                    bloodgrp = "A-";
                    break;
                case 5:
                    bloodgrp = "B+";
                    break;
                case 6:
                    bloodgrp = "B-";
                    break;
                case 7:
                    bloodgrp = "AB+";
                    break;
                case 8:
                    bloodgrp = "AB-";
                    break;



            }
        }
        else
            Toast.makeText(getApplicationContext(), "Select a BloodGroup", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub


    }

    private void register_user(final String displayName, final String email, String pass) {

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Prof pro = new Prof(displayName,email,bloodgrp,phone);
                    FirebaseDatabase.getInstance().getReference("Prof").child(FirebaseAuth.getInstance()
                            .getCurrentUser().getUid()).setValue(pro).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this,"Successfully Registered....",Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                    });
                   // Toast.makeText(RegisterActivity.this,"Successfully Registered....",Toast.LENGTH_LONG).show();
                    //Intent regIntent = new Intent(RegisterActivity.this,MainActivity.class);
                   // startActivity(regIntent);
                    //finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"You got some Error..",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
