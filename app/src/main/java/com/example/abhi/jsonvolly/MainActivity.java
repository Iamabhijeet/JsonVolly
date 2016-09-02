package com.example.abhi.jsonvolly;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhi.jsonvolly.RealmManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.internal.Context;

public class MainActivity extends AppCompatActivity {
        TextView tvData,tvId,tvName;
        Button btEnter,btSaveRealm;
        EditText etUser,etPass;
        String url="http://66.85.152.27:8080/myMobileClass/signup";
        RequestQueue requestQueue;
        Realm realm;
//        Context context;
//        android.content.Context con;
//        AlertDialog.Builder builder;
//        String username,password,regid,schemaname;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData=(TextView)findViewById(R.id.tvData);
        tvId=(TextView)findViewById(R.id.tvId);
        tvName=(TextView)findViewById(R.id.tvName);
        btEnter=(Button)findViewById(R.id.btEnter);
        btSaveRealm=(Button)findViewById(R.id.btSaveRealm);
        etUser=(EditText)findViewById(R.id.etUser);
        etPass=(EditText)findViewById(R.id.etPass);
        Cache cache=new DiskBasedCache(getCacheDir(),1024*1024);
        Network network=new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache,network);
        requestQueue.start();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded().build();
        final Realm realm = Realm.getInstance(realmConfig);
//        realm=Realm.getInstance(context);



        btEnter.setOnClickListener(new View.OnClickListener() {
//            String name=etUser.getText().toString();
//            String pass=etPass.getText().toString();

            @Override
            public void onClick(View view) {

                requestQueue= Volley.newRequestQueue(MainActivity.this);
                JSONObject params = new JSONObject();
                try {

                    params.put("name","Amol");
                    params.put("emailid" , "amol.dhole@qaagility.com");
                    params.put("password" ,"abc");
                    params.put("div" , "Morning");
                    params.put("AcademicYear" , "2015");
                    params.put("Standard","Phonics");
                    params.put("schemaname","phonickids");

//                    params.put("username", name);
//                    params.put("password",pass);
//                    params.put("regid","");
//                    params.put("schemaname","phonickids");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, url, params,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
//                        stopLoading();

                                try {
                                    String Id = response.getString("uniqueid");
                                    String Name=response.getString("status");
                                    String Email = response.getString("errorMessage");
                                    tvId.setText(Id);
                                    tvName.setText(Name);
                                    tvData.setText(Email);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //tvData.setText(response.toString());

                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                tvData.setText("Error");
                            }
                        }

                );

//                showLoadingView();
                RetryPolicy policy = new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                loginRequest.setRetryPolicy(policy);
                requestQueue.add(loginRequest);

            }

        });

        btSaveRealm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String id=tvId.getText().toString();
                final String name=tvName.getText().toString();
                final String email=tvData.getText().toString();











//                String[] names = getResources().getStringArray(R.array.names);
//                String[] majors = getResources().getStringArray(R.array.majors);
//                String[] years = getResources().getStringArray(R.array.years);
//                realm.executeTransaction(new Realm.Transaction() {
//                    Person p = new Person();
//                    @Override
//                    public void execute(Realm realm) {
//                            Person person=realm.createObject(Person.class);
//
//
                          Person person = new Person();
                            person.setUserID(id);
                            person.setUserName(name);
                            person.setUserEmail(email);

                        realm.beginTransaction();
                        realm.copyToRealm(person);
                        realm.commitTransaction();
                Intent R=new Intent(MainActivity.this,ShowData.class);
                startActivity(R);
                Toast.makeText(MainActivity.this, "Saved Into Database", Toast.LENGTH_SHORT).show();
//
//
//
//
//
//                           // realm.insertOrUpdate(person);
//                        }
//                    });





            }
        });

    }
}
