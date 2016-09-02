package com.example.abhi.jsonvolly;

import android.app.Application;
import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by abhi on 01-09-2016.
 */
public class BaseDataClass  extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            // The Realm file will be located in Context.getFilesDir() with name "default.realm"
            RealmConfiguration config = new RealmConfiguration.Builder(this).build();
            Realm.setDefaultConfiguration(config);
        }
    }

