package com.islamabad.timing.namaztimings;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.islamabad.timing.namaztimings.model.Modelist;
import com.islamabad.timing.namaztimings.model.Mosque;
import com.islamabad.timing.namaztimings.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("1").child("fajar").setValue("hdhdh");
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                GenericTypeIndicator<List<Mosque>> t = new GenericTypeIndicator<List<Mosque>>() {};
//                List<Mosque> messages = dataSnapshot.getValue(t);
//                String s = ""+messages.toString();
//                Log.d("TAAAG", GsonUtils.toJson(messages.get(1)));
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }
}
