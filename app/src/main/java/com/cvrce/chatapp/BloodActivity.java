package com.cvrce.chatapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BloodActivity extends AppCompatActivity {

    Button mShowdialog;
    private FirebaseAuth firebaseAuth;
    private Toolbar mtool;
    LocationManager lm;
    //DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Req").child(firebaseAuth.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);
        mtool = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mtool);
        // mtool.setTitle("BloodRequest");
        try{
            lm = (LocationManager)getSystemService(LOCATION_SERVICE);
            lm.requestLocationUpdates("gps",60000,0,new LocationHander());

        }catch (SecurityException r){
            r.printStackTrace();
        }



        mShowdialog = (Button)findViewById(R.id.btnreq);

        mShowdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(BloodActivity.this);
                View mview  = getLayoutInflater().inflate(R.layout.activity_request,null);
                mBuilder.setTitle("Choose a Blood Group");
                final Spinner mSpinner  = (Spinner)mview.findViewById(R.id.reqSpinner);

                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(BloodActivity.this,android.R.layout.simple_spinner_item,(getResources().getStringArray(R.array.BloodGroupList)));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);

                mBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Blood Group"))
                        {
                            Toast.makeText(BloodActivity.this,"Selected Blood Group is : "
                                    +mSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

                            try{

                                final Location lastLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                FirebaseDatabase.getInstance().getReference("Req").child(FirebaseAuth.getInstance()
                                        .getCurrentUser().getUid()).setValue(new Req(lastLocation.getLatitude(),lastLocation.getLongitude(),mSpinner.getSelectedItem().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

//                                        Intent intent = new Intent(BloodActivity.this,RequestAcceptedList.class);
//                                        startActivity(intent);
//                                        finish();
                                        Toast.makeText(BloodActivity.this,"Successfully Requested",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }catch (SecurityException e){
                                e.printStackTrace();
                            }



                        }
                        else
                        {
                            Toast.makeText(BloodActivity.this,"Kindly Select a blood Group",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setView(mview);
                AlertDialog adialog = mBuilder.create();
                adialog.show();


            }
        });

    }

    public void onDonateClick(View view) {
        Intent i = new Intent(this,DonorActivity.class);
        startActivity(i);
    }



    public void onInfoClick(View view) {
        Intent i = new Intent(this,InfoActivity.class);
        startActivity(i);
    }
}
