package com.tinaio.tianapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tinaio.tianapp.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button btn_login,btn_register;
    public static String[] LOGIN_TYPE = new String[]{"login", "SingUp"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewItems();
    }

    private void ViewItems() {
        username = findViewById(R.id.phone_number);
        password = findViewById(R.id.user_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CheckUsernameandPassword(username,password,LOGIN_TYPE[1]);
                    }
                });
                thread.start();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckUsernameandPassword(username,password,LOGIN_TYPE[0]);
            }
        });
    }

    private void CheckUsernameandPassword(EditText username, EditText password,String type) {
        new Asyncetask().execute(username.getText().toString(),password.getText().toString(),type);
    }

    private void SaveInDataBase() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Username", username.getText().toString());
        editor.putString("Password", password.getText().toString());
        editor.commit();
    }
    public class Asyncetask extends AsyncTask<String ,String ,String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String content;
            StringBuilder sb =new StringBuilder();
            sb.append("{\n" +
                    "\"username\":\"")
                    .append(params[0])
                    .append("\",\n" +
                            " \"password\":\"")
                    .append(params[1])
                    .append("\"\n" +
                            "}");
            content = sb.toString();
            Log.i("sdfkalsjdfl",content);
            if (params[2].equals(LoginActivity.LOGIN_TYPE[1])) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, content);
                Request request = new Request.Builder()
                        .url("https://api.tinaio.info/register")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    Log.i("sakfjlasf",response.message());
                    return String.valueOf(response.code());
                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
            else {
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
                    Log.i("sakfjlasf",response.message());
                    return String.valueOf(response.code());
                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("409"))
                Toast.makeText(LoginActivity.this,"this user is already exist",Toast.LENGTH_SHORT).show();
            else if (result.equals("401")) {
                Toast.makeText(LoginActivity.this, "Invalid UserName Or Password", Toast.LENGTH_SHORT).show();
            }
            else if (result.equals("404"))
                Toast.makeText(LoginActivity.this, "Not Found!", Toast.LENGTH_SHORT).show();
            else if (result.equals("200")) {
                SaveInDataBase();
                finish();
            }
        }
    }
}
