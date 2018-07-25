package com.islamabad.timing.namaztimings;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.islamabad.timing.namaztimings.model.Mosque;
import com.islamabad.timing.namaztimings.utils.AppUtils;
import com.islamabad.timing.namaztimings.utils.Constants;

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
    TextView loadingText;
    LinearLayout loader;
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
        loader = (LinearLayout) findViewById(R.id.loader);
        loadingText = (TextView) findViewById(R.id.loading_text);
        buttonSave.setOnClickListener(this);
        buttonSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showLoader("Saving...");
                return false;
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        hideLoader();
        if(AppUtils.isInternetAvailable(getApplicationContext())){
            showLoader("Getting Current Timings");
        }
        mDatabase.child(Constants.MOSQUE_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hideLoader();
                Mosque mosque =(Mosque) dataSnapshot.getValue(Mosque.class);
                if(mosque!=null){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        timePickerFajar.setHour(Integer.parseInt(mosque.fajar.split(":")[0]));
                        timePickerFajar.setMinute(Integer.parseInt(mosque.fajar.split(":")[1]));
                        timePickerZohar.setHour(Integer.parseInt(mosque.zohar.split(":")[0]));
                        timePickerZohar.setMinute(Integer.parseInt(mosque.zohar.split(":")[1]));
                        timePickerAsar.setHour(Integer.parseInt(mosque.asar.split(":")[0]));
                        timePickerAsar.setMinute(Integer.parseInt(mosque.asar.split(":")[1]));
                        timePickerMagrib.setHour(Integer.parseInt(mosque.magrib.split(":")[0]));
                        timePickerMagrib.setMinute(Integer.parseInt(mosque.magrib.split(":")[1]));
                        timePickerIsha.setHour(Integer.parseInt(mosque.isha.split(":")[0]));
                        timePickerIsha.setMinute(Integer.parseInt(mosque.isha.split(":")[1]));
                        timePickerJuma.setHour(Integer.parseInt(mosque.juma.split(":")[0]));
                        timePickerJuma.setMinute(Integer.parseInt(mosque.juma.split(":")[1]));
                        timePickerEid.setHour(Integer.parseInt(mosque.eid.split(":")[0]));
                        timePickerEid.setMinute(Integer.parseInt(mosque.eid.split(":")[1]));
                    }else {
                        timePickerFajar.setCurrentHour(Integer.parseInt(mosque.fajar.split(":")[0]));
                        timePickerFajar.setCurrentMinute(Integer.parseInt(mosque.fajar.split(":")[1]));
                        timePickerZohar.setCurrentHour(Integer.parseInt(mosque.zohar.split(":")[0]));
                        timePickerZohar.setCurrentMinute(Integer.parseInt(mosque.zohar.split(":")[1]));
                        timePickerAsar.setCurrentHour(Integer.parseInt(mosque.asar.split(":")[0]));
                        timePickerAsar.setCurrentMinute(Integer.parseInt(mosque.asar.split(":")[1]));
                        timePickerMagrib.setCurrentHour(Integer.parseInt(mosque.magrib.split(":")[0]));
                        timePickerMagrib.setCurrentMinute(Integer.parseInt(mosque.magrib.split(":")[1]));
                        timePickerIsha.setCurrentHour(Integer.parseInt(mosque.isha.split(":")[0]));
                        timePickerIsha.setCurrentMinute(Integer.parseInt(mosque.isha.split(":")[1]));
                        timePickerJuma.setCurrentHour(Integer.parseInt(mosque.juma.split(":")[0]));
                        timePickerJuma.setCurrentMinute(Integer.parseInt(mosque.juma.split(":")[1]));
                        timePickerEid.setCurrentHour(Integer.parseInt(mosque.eid.split(":")[0]));
                        timePickerEid.setCurrentMinute(Integer.parseInt(mosque.eid.split(":")[1]));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        mDatabase.child("1").setValue(mosque).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//            }
//        });
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

    public void showLoader(String text){
        loadingText.setText(text);
        loader.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        loader.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View view) {
        Mosque mosque = new Mosque();
        switch (view.getId()){
            case R.id.save_btn:
                showLoader("Saving...");
                if(Build.VERSION.SDK_INT < 23){
                    mosque.fajar=""+timePickerFajar.getCurrentHour()+":"+timePickerFajar.getCurrentMinute();
                    mosque.zohar=""+timePickerZohar.getCurrentHour()+":"+timePickerZohar.getCurrentMinute();
                    mosque.asar=""+timePickerAsar.getCurrentHour()+":"+timePickerAsar.getCurrentMinute();
                    mosque.magrib=""+timePickerMagrib.getCurrentHour()+":"+timePickerMagrib.getCurrentMinute();
                    mosque.isha=""+timePickerIsha.getCurrentHour()+":"+timePickerIsha.getCurrentMinute();
                    mosque.juma=""+timePickerJuma.getCurrentHour()+":"+timePickerJuma.getCurrentMinute();
                    mosque.eid=""+timePickerEid.getCurrentHour()+":"+timePickerEid.getCurrentMinute();
                    mosque.last_updated=""+AppUtils.getDateAndTime();

                } else{
                    mosque.fajar=""+timePickerFajar.getHour()+":"+timePickerFajar.getMinute();
                    mosque.zohar=""+timePickerZohar.getHour()+":"+timePickerZohar.getMinute();
                    mosque.asar=""+timePickerAsar.getHour()+":"+timePickerAsar.getMinute();
                    mosque.magrib=""+timePickerMagrib.getHour()+":"+timePickerMagrib.getMinute();
                    mosque.isha=""+timePickerIsha.getHour()+":"+timePickerIsha.getMinute();
                    mosque.juma=""+timePickerJuma.getHour()+":"+timePickerJuma.getMinute();
                    mosque.eid=""+timePickerEid.getHour()+":"+timePickerEid.getMinute();
                    mosque.last_updated=""+AppUtils.getDateAndTime();

                }
                mosque.name= Constants.MOSQUE_NAME;
                mDatabase.child(Constants.MOSQUE_ID).setValue(mosque).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        AppUtils.makeToast(SplashActivity.this,"Updated Successfully");
                        hideLoader();
                        finish();
                    }
                });
                if(!AppUtils.isInternetAvailable(getApplicationContext())){
                    AppUtils.makeToast(SplashActivity.this,"Timings will update when internet is available");
                    finish();
                }
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
