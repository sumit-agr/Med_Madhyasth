package com.cvrce.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
private TextView profilename,profileemail,profilebloodgrp,profilephone;
    private DatabaseReference user;
    private FirebaseDatabase firebasedatabase;
    private FirebaseAuth firebaseAuth;
    ArrayList<String> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilename = (TextView)findViewById(R.id.pname);
        profileemail = (TextView)findViewById(R.id.pemail);
        profilebloodgrp = (TextView)findViewById(R.id.pbg);
        profilephone = (TextView) findViewById(R.id.phn);
        firebaseAuth = FirebaseAuth.getInstance();
        firebasedatabase = FirebaseDatabase.getInstance();
        DatabaseReference db1 = firebasedatabase.getReference("Prof").child(firebaseAuth.getUid());
       db1.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot child:dataSnapshot.getChildren())
               {
                   arr.add(child.getValue().toString());
               }

               profilename.setText(arr.get(3));
               profileemail.setText(arr.get(1));
               profilebloodgrp.setText(arr.get(0));
               profilephone.setText(arr.get(2));
           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
  }
}
