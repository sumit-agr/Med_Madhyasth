package com.cvrce.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DonorActivity extends AppCompatActivity {

    ListView itemList;
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseAuth firebase;
    List<String> list = new ArrayList<String>() ;
    ArrayAdapter adapter;
    Double latitude,longitude;
    String bloodgroup;
    DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Req");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        //itemList.getSolidColor();


        itemList = (ListView)findViewById(R.id.donor_lstView);


        adapter  = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list);





        itemList.setAdapter(adapter);
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Req obj;
                Iterator i = dataSnapshot.getChildren().iterator();
                HashMap<String,String> map = new HashMap<String,String>();
                while(i.hasNext()){
                    String str = ((DataSnapshot)i.next()).getValue().toString();
                    set.add(str);
                }
                adapter.clear();
                adapter.addAll(set);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Intent i = new Intent(getApplicationContext(),FromDonorToNeedy.class);
                                                i.putExtra("Data",((TextView)view).getText().toString());
                                                startActivity(i);
                                            }
                                        }

        );
    }
}
