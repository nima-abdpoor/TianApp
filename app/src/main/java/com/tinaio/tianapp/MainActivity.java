package com.tinaio.tianapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.tinaio.tianapp.login.LoginActivity;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                CheckUser();
            }
        });
        thread.start();
    }

    private void CheckUser() {
        String content;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String username = pref.getString("Username","-1");
        String password = pref.getString("Password","-1");
        Log.i("sdalkfsadjlf",username+password);
        if (username.equals("-1") || password.equals("-1")){
            Intent intent=new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else {
            StringBuilder sb =new StringBuilder();
            sb.append("{\n" +
                    "\"username\":\"")
                    .append(username)
                    .append("\",\n" +
                            " \"password\":\"")
                    .append(password)
                    .append("\"\n" +
                            "}");
            content = sb.toString();
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, content);
            Request request = new Request.Builder()
                    .url("https://api.tinaio.info/login")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.code() == 200)
                    return;
                else{
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
