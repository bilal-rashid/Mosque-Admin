package com.islamabad.timing.namaztimings;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.islamabad.timing.namaztimings.api.ApiClient;
import com.islamabad.timing.namaztimings.api.ApiInterface;
import com.islamabad.timing.namaztimings.model.Contents;
import com.islamabad.timing.namaztimings.model.Data;
import com.islamabad.timing.namaztimings.model.Filter;
import com.islamabad.timing.namaztimings.model.Mosque;
import com.islamabad.timing.namaztimings.model.Notification;
import com.islamabad.timing.namaztimings.utils.AppUtils;
import com.islamabad.timing.namaztimings.utils.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

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
    EditText notes;
    LinearLayout loader;
    Mosque globalMosque;

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
        notes = (EditText) findViewById(R.id.edit_text_notes);
        loader = (LinearLayout) findViewById(R.id.loader);
        loadingText = (TextView) findViewById(R.id.loading_text);
        buttonSave.setOnClickListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        hideLoader();
        if (AppUtils.isInternetAvailable(getApplicationContext())) {
            showLoader("Getting Current Timings");
        }
        mDatabase.child(Constants.MOSQUE_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Mosque mosque = (Mosque) dataSnapshot.getValue(Mosque.class);
                if(globalMosque==null) {
                    globalMosque = mosque;
                    hideLoader();
                }
                if (mosque != null) {
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
                    } else {
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
                    notes.setText(""+mosque.notes);
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

    public void showLoader(String text) {
        loadingText.setText(text);
        loader.setVisibility(View.VISIBLE);
    }

    public void hideLoader() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        final Mosque mosque = new Mosque();
        switch (view.getId()) {
            case R.id.save_btn:
                showLoader("Saving...");
                if (Build.VERSION.SDK_INT < 23) {
                    mosque.fajar = "" + timePickerFajar.getCurrentHour() + ":" + timePickerFajar.getCurrentMinute();
                    mosque.zohar = "" + timePickerZohar.getCurrentHour() + ":" + timePickerZohar.getCurrentMinute();
                    mosque.asar = "" + timePickerAsar.getCurrentHour() + ":" + timePickerAsar.getCurrentMinute();
                    mosque.magrib = "" + timePickerMagrib.getCurrentHour() + ":" + timePickerMagrib.getCurrentMinute();
                    mosque.isha = "" + timePickerIsha.getCurrentHour() + ":" + timePickerIsha.getCurrentMinute();
                    mosque.juma = "" + timePickerJuma.getCurrentHour() + ":" + timePickerJuma.getCurrentMinute();
                    mosque.eid = "" + timePickerEid.getCurrentHour() + ":" + timePickerEid.getCurrentMinute();
                    mosque.last_updated = "" + AppUtils.getDateAndTime();

                } else {
                    mosque.fajar = "" + timePickerFajar.getHour() + ":" + timePickerFajar.getMinute();
                    mosque.zohar = "" + timePickerZohar.getHour() + ":" + timePickerZohar.getMinute();
                    mosque.asar = "" + timePickerAsar.getHour() + ":" + timePickerAsar.getMinute();
                    mosque.magrib = "" + timePickerMagrib.getHour() + ":" + timePickerMagrib.getMinute();
                    mosque.isha = "" + timePickerIsha.getHour() + ":" + timePickerIsha.getMinute();
                    mosque.juma = "" + timePickerJuma.getHour() + ":" + timePickerJuma.getMinute();
                    mosque.eid = "" + timePickerEid.getHour() + ":" + timePickerEid.getMinute();
                    mosque.last_updated = "" + AppUtils.getDateAndTime();

                }
                mosque.name = Constants.MOSQUE_NAME;
                mosque.location = Constants.MOSQUE_LOCATION;
                mosque.id = Constants.MOSQUE_ID;
                mosque.notes = notes.getText().toString();
                if(globalMosque!=null) {
                    if (!globalMosque.equals(mosque)) {
                        mDatabase.child(Constants.MOSQUE_ID).setValue(mosque).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                AppUtils.makeToast(SplashActivity.this, "Updated Successfully");
                                showLoader("Sending Notification...");
                                sendNotification(mosque);

                            }
                        });
                        if (!AppUtils.isInternetAvailable(getApplicationContext())) {
                            AppUtils.makeToast(SplashActivity.this, "Timings will update when internet is available");
                            finish();
                        }
                    } else {
                        hideLoader();
                        AppUtils.makeToast(getApplicationContext(), "Nothing Changed");
                    }
                }else {
                    mDatabase.child(Constants.MOSQUE_ID).setValue(mosque).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            AppUtils.makeToast(SplashActivity.this, "Updated Successfully");
                            showLoader("Sending Notification...");
                            sendNotificationToAll();

                        }
                    });
                    if (!AppUtils.isInternetAvailable(getApplicationContext())) {
                        AppUtils.makeToast(SplashActivity.this, "Timings will update when internet is available");
                        finish();
                    }

                }
                break;
        }

    }

    private void sendNotificationToAll() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Notification request = new Notification();
        request.app_id="a5e83790-9b65-4ccb-80eb-9cb27c809b58";
        request.contents=new Contents();
        request.contents.en="New Mosque Added\n"+Constants.MOSQUE_NAME;
        request.data = new Data();
        request.data.data="data";
        request.included_segments=new ArrayList<>();
        request.included_segments.add("All");
        Call<Object> call = apiService.postPackets(request);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                hideLoader();
                finish();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAAAG", "" + t.getMessage());

            }
        });
    }

    public void sendNotification(Mosque mosque) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Notification request = new Notification();
        request.app_id="a5e83790-9b65-4ccb-80eb-9cb27c809b58";
        request.contents=new Contents();
        request.contents.en=Constants.MOSQUE_NAME+"\n"+globalMosque.compare(mosque);
        request.data = new Data();
        request.data.data="data";
        Filter filter=new Filter();
        filter.field="tag";
        filter.key=Constants.MOSQUE_ID;
        filter.relation="=";
        filter.value="1";
        request.filters=new ArrayList<>();
        request.filters.add(filter);
//        Log.d("TAAAG",""+GsonUtils.toJson(request));
        Call<Object> call = apiService.postPackets(request);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
//                    Log.d("TAAAG",GsonUtils.toJson(response.body()));
                hideLoader();
                finish();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAAAG", "" + t.getMessage());

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
