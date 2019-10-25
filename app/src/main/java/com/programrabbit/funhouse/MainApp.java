package com.programrabbit.funhouse;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/lucida_sans_demibold_roman.tff")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}