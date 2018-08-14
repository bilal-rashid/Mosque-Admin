package com.islamabad.timing.namaztimings;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by Bilal Rashid on 8/14/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
