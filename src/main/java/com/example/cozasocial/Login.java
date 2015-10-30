package com.example.cozasocial;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;



public class Login extends Activity
{

    EditText username1;
    EditText password1;
    InputStream is;
    String username;
    String password;
    final String sharedPrefFileName = "file";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    String emailadd;
    String url;
    ProgressDialog progressDial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(sharedPrefFileName, 0);
        editor = prefs.edit();
        setContentView(R.layout.activity_login);
    }

    public void registeration(View view) {
        Intent a = new Intent(Login.this, Register.class);

        startActivity(a);
        overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);
    }

    public void logmein(View view) {

            username1 = (EditText) findViewById(R.id.username_login);
            password1 = (EditText) findViewById(R.id.password_login);
            username = username1.getText().toString();
            password = password1.getText().toString();

            if (username.length() < 2 || password.length() < 2) {
                YoYo.with(Techniques.Shake)
                        .duration(600)
                        .playOn(findViewById(R.id.animeTest));
                Toast.makeText(getApplicationContext(), "Passwords have to be 2 characters or more, Passwords have to be 2 characters or more", Toast.LENGTH_LONG).show();
            } else {
                try {
                    progressDial = new ProgressDialog(Login.this);
                    progressDial.setTitle("Processing");
                    progressDial.setMessage("Hold on Please :)");
                    progressDial.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDial.show();
                    url = "http://www.samuelagbede.com/coza_social/check_login.php?username=" + username + "&password=" + password;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            try {
                JsonArrayRequest jsonObject = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) throws JSONException {
                        JSONObject json = null;
                        try {

                            json = response.getJSONObject(0);
                            emailadd = json.getString("email_address");
                            String url = json.getString("profile_picture");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (json.getString("username").equalsIgnoreCase(username)) {




                              editor.putString("status", "1");
                               editor.putString("username", username);
                               editor.putString("password", password);
                                editor.putString("email_address", emailadd);

                                editor.commit();
                                startagain();
                            }
                            else
                            {
                                toast();
                                progressDial.hide();

                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                                           toast1();
                    }
                }
                );

                Singleton.getInstance(this).addToRequestQueue(jsonObject);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    private void toast1() {
        Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_LONG).show();
    }

    public void toast() {
        Toast.makeText(this, "You do not have an account with us, please try registering!", Toast.LENGTH_LONG).show();
    }

    public void startagain() {

        Intent a = new Intent(this, Featured.class);
        a.putExtra("username", username);
        a.putExtra("password", password);
        a.putExtra("emailadd", emailadd);
        startActivity(a);
    }


}
