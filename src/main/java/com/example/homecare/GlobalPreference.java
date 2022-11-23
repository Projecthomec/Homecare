package com.example.homecare;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class GlobalPreference {
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    private Context context;
    public GlobalPreference(Context context) {
        sharedPreference = context.getSharedPreferences("sample",MODE_PRIVATE);
        editor = sharedPreference.edit();
    }
    public void saveIp(String ipaddress){
        editor.putString("ip",ipaddress);
        editor.apply();
    }
    public String getIp(){
        return  sharedPreference.getString("ip","");
    }

    public void saveID(String uid){
        editor.putString("user_id",uid);
        editor.apply();
    }
    public String getID(){
        return  sharedPreference.getString("user_id","");
    }
}
