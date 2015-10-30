package com.example.cozasocial;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.pushbots.push.Pushbots;

public class Splash extends Activity {
    final String sharedPrefFileName = "file";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        prefs = getSharedPreferences(sharedPrefFileName, 0);
        editor = prefs.edit();


        Pushbots.sharedInstance().init(this);

        if(prefs.contains("status")) {

            if (prefs.getString("status", null).equalsIgnoreCase("1")) {
                Log.d("It is there", "Contains 1");
                Intent a = new Intent(Splash.this, Featured.class);
                a.putExtra("username", prefs.getString("username", null));
                a.putExtra("emailadd", prefs.getString("email_address", null));
                startActivity(a);
            }
            //   }
            else {
                startActivity(new Intent(Splash.this, Login.class));
            }
        }
        else
        {
            editor.putString("status", "0");
            editor.commit();
            startActivity(new Intent(Splash.this, Login.class));

        }

    }


	/*	Thread runa  = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                   // finish();
                }
              //  if (prefs.contains("status"))
                //{


                runa.start();
            }

        };

*/

}
	