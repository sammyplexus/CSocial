package com.example.cozasocial;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class Register extends Activity {


    EditText username1;
    EditText email_add1;
    EditText password1;
    Button register;
    ProgressDialog progressDial;
    public String username;
    public String email_add;
    public String password;
    final String sharedPrefFileName = "file";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setContentView(R.layout.register);
        super.onCreate(savedInstanceState);

        username1 = (EditText) findViewById(R.id.username_signup);
        email_add1 = (EditText) findViewById(R.id.email_signup);
        password1 = (EditText) findViewById(R.id.password_signup);
        register = (Button) findViewById(R.id.signup_register);
        final TextView blank = (TextView) findViewById(R.id.hey);

        Log.d("Worked", "It worked! Thanks to God");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if ((username1.getText().toString() == null && email_add1.getText().toString() == null) || password1.getText().toString() == null) {
                    blank.setText("Please enter all your details correctly");}else{
                    username = username1.getText().toString();
                    email_add = email_add1.getText().toString();
                    password = password1.getText().toString();


                    String url = "http://www.samuelagbede.com/coza_social/signup.php?username=" + username + "&email_add=" + email_add + "&password=" + password;
                    progressDial = new ProgressDialog(Register.this);
                    progressDial.setTitle("Loading");
                    progressDial.setMessage("Please wait, we are processing your request");
                    progressDial.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDial.show();
                    registerStart(url);
                    progressDial.hide();



                    Toast.makeText(getApplicationContext(), "You just signed up! Yay!", Toast.LENGTH_LONG).show();
                    Intent reg = new Intent(Register.this, Featured.class);
                    reg.putExtra("username", username);
                    reg.putExtra("password", password);




                    prefs = getSharedPreferences(sharedPrefFileName, 0);
                    editor = prefs.edit();
                    prefs = getSharedPreferences(sharedPrefFileName, 0);
                    editor = prefs.edit();
                    editor.putString("username",username);
                    editor.putString("password",password);
                   // editor.putString("email_address", email_address);
                    editor.commit();
                    Log.d("Done", "Entered successfully");

                    startActivity(reg);
                }
            }
        });
    }

    public void registerStart(String url){

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Singleton.getInstance(this).addToRequestQueue(jor);


    }


}