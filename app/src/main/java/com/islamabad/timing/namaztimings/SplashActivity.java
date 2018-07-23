package com.islamabad.timing.namaztimings;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.islamabad.timing.namaztimings.model.Mosque;
import com.islamabad.timing.namaztimings.utils.AppUtils;
import com.islamabad.timing.namaztimings.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mDatabase;
    Button buttonSave;
    TimePicker timePickerFajar;
    TimePicker timePickerZohar;
    TimePicker timePickerAsar;
    TimePicker timePickerMagrib;
    TimePicker timePickerIsha;
    TimePicker timePickerJuma;
    TimePicker timePickerEid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        buttonSave = (Button) findViewById(R.id.save_btn);
        timePickerFajar = (TimePicker) findViewById(R.id.timePicker_fajar);
        timePickerZohar = (TimePicker) findViewById(R.id.timePicker_zohar);
        timePickerAsar = (TimePicker) findViewById(R.id.timePicker_asar);
        timePickerMagrib = (TimePicker) findViewById(R.id.timePicker_magrib);
        timePickerIsha = (TimePicker) findViewById(R.id.timePicker_isha);
        timePickerJuma = (TimePicker) findViewById(R.id.timePicker_juma);
        timePickerEid = (TimePicker) findViewById(R.id.timePicker_eid);
        buttonSave.setOnClickListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Mosque mosque = new Mosque();
        mosque.fajar="dd";
        mosque.zohar="dd";
        mosque.asar="d";
        mosque.magrib="dasd";
        mosque.isha="asdas";
        mosque.juma="saf";
        mosque.eid="asfas";
        mosque.location="fdsf";
        mosque.name="Tauheed";
        mosque.last_updated="sdf";

//        mDatabase.child("1").setValue(mosque).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//            }
//        });
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Mosque>> t = new GenericTypeIndicator<List<Mosque>>() {};
                List<Mosque> messages = dataSnapshot.getValue(t);
                String s = ""+messages.toString();
                Log.d("TAAAG", GsonUtils.toJson(messages.get(1)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        AppUtils.makeToast(this,"");
        switch (view.getId()){
            case R.id.save_btn:
                if(Build.VERSION.SDK_INT < 23){
                    int getHour = timePickerFajar.getCurrentHour();
                    int getMinute = timePickerFajar.getCurrentMinute();
                    Log.d("TAAAG",getHour+"");
                    Log.d("TAAAG",getMinute+"");

                } else{
                    int getHour = timePickerFajar.getHour();
                    int getMinute = timePickerFajar.getMinute();
                    Log.d("TAAAG",getHour+"");
                    Log.d("TAAAG",getMinute+"");

                }
                break;
        }

    }
}
