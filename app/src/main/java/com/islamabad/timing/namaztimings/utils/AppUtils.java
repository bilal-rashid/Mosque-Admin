package com.islamabad.timing.namaztimings.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bilal Rashid on 7/24/2018.
 */

public class AppUtils {
    public static String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c.getTime());
    }
    public static String getDateAndTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
        return df.format(c.getTime());
    }
    public static String getMosqueTime(String time){
        Calendar c = Calendar.getInstance();
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        c.set(2000,12,12,hour,minute);
        SimpleDateFormat df = new SimpleDateFormat(Constants.TIME_FORMAT);
        return df.format(c.getTime());
    }
    public static boolean isInternetAvailable(final Context context) {
        ConnectivityManager conn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = conn.getActiveNetworkInfo();
        if (activeNetworkInfo != null
                && activeNetworkInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    public static void makeToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
