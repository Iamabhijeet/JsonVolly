package com.example.abhi.jsonvolly;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by abhi on 01-09-2016.
 */
public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mctx;




    private MySingleton (Context context){
        mctx= context;
        requestQueue= getRequestQueue();
    }


    public RequestQueue getRequestQueue() {

        if (requestQueue == null) {

            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());

        }
        return (requestQueue);
    }
    public static synchronized MySingleton getmInstance(Context context){
        if(mInstance==null)
         {

        mInstance=new MySingleton(context);

         }
         return mInstance;

        }

    public <T>void addToRequestQueue(Request<T> requset){

                requestQueue.add(requset);
            }
}
