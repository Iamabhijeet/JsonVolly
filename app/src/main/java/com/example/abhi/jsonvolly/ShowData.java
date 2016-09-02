package com.example.abhi.jsonvolly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.abhi.jsonvolly.relm.DBLiveFeeds;
//import com.example.abhi.jsonvolly.relm.LiveFeedsStr;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ShowData extends AppCompatActivity {
    TextView tvData,tvId,tvName;
    Button btBack;
    //Realm realmS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        tvData=(TextView)findViewById(R.id.tvSData);
        tvId=(TextView)findViewById(R.id.tvSId);
        tvName=(TextView)findViewById(R.id.tvSName);
        btBack=(Button)findViewById(R.id.btBack);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded().build();
        final Realm realmS = Realm.getInstance(realmConfig);



        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                RealmResults<Person> personDB = realmS.where(Person.class).findAll();
                if (personDB.size() != 0){
                  //  ArrayList<Person> data = new ArrayList<>();

                    for (int i=0;i<personDB.size();i++){

                        String dataR = personDB.get(i).getUserEmail();
                        String idR =personDB.get(i).getUserID();
                        String nameR =personDB.get(i).getUserName();

                        tvData.setText(dataR);
                        tvId.setText(idR);
                        tvName.setText(nameR);
                    }



                }
            }
                    });
    }
}
