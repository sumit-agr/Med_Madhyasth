package com.cvrce.chatapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cvrce.chatapp.ui.DPActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  requestPermissions(new String[]{Manifest.permissions.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,},0);
    requestPermissions(new String[]{Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0);
        mAuth = FirebaseAuth.getInstance();

        mtoolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mtoolbar);
        mtoolbar.setTitle("MedMadhyasth");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
        {
            sendToStart();
        }

    }

    private void sendToStart() {

        Intent startIntent = new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.main_menu,menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

         if(item.getItemId()==R.id.main_logout)
         {
            FirebaseAuth.getInstance().signOut();
            sendToStart();
         }
        else if(item.getItemId()==R.id.main_account_bar)
         {
             sendToProfile();
         }

         return true;
    }

    private void sendToProfile() {
        Intent i = new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(i);
        //finish();


    }

    public void bldclick(View view) {
        Intent i = new Intent(this,BloodActivity.class);
        startActivity(i);
    }

    public void dpclick(View view) {
        Intent i = new Intent(this, DPActivity.class);
        startActivity(i);
    }
}
