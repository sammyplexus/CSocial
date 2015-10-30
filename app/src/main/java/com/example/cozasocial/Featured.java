package com.example.cozasocial;
import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class Featured extends ActionBarActivity
{

    private ListView nav_drawer;
    private FeaturedAdapterListNav content;
    private DrawerLayout dl;
    String comments1;
    public String username1;
    public TextView name;
    public TextView emailadd;
    final String sharedPrefFileName = "file";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        abdt.onConfigurationChanged(newConfig);
    }



    public String password1;
    public String phone_number1;
    public ImageView imgView;
    static final int SELFIE = 1 ;
    static final int SELECT_FILE = 2;
    public String location1;
    public String email_address1;
    TextView phone_number;
    TextView location;
    Intent intent;
    LinearLayout layout;
    String connectUrl;
    String imagePath = null;
    ActionBarDrawerToggle abdt;
   private Toolbar toolbar;
    ProgressDialog progressDialog;
    Integer[] pictures = {R.drawable.featured, R.drawable.devotional1,  R.drawable.publications, R.drawable.news_and_events,R.drawable.about, R.drawable.my_profile};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prefs = getSharedPreferences(sharedPrefFileName, 0);
        editor = prefs.edit();

        setContentView(R.layout.featured);

        intent = getIntent();
        username1 = intent.getStringExtra("username");
      //  password1 = intent.getStringExtra("password");
        email_address1 = intent.getStringExtra("emailadd");
        start();
        action_man();
        makeactionbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        abdt.syncState();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    private void makeactionbar() {
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setLogo(getResources().getDrawable(R.drawable.ic_launcher));
        setSupportActionBar(toolbar);

       abdt = new ActionBarDrawerToggle(
              this,
             dl,
             R.string.drawer_open,
               R.string.drawer_close
      ){

           @Override
           public void onDrawerOpened(View drawerView) {
               super.onDrawerOpened(drawerView);
               getSupportActionBar().setTitle("Navigation");
               invalidateOptionsMenu();
           }

           @Override
           public void onDrawerClosed(View drawerView) {
               super.onDrawerClosed(drawerView);
               getSupportActionBar().setTitle("COZA Social");
               invalidateOptionsMenu();
           }
       };
        dl.setDrawerListener(abdt);
        abdt.setDrawerIndicatorEnabled(true);



        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (abdt.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void comments_enter(View view){
        progressDialog.setMessage("Please hold on");
        progressDialog.show();
        final TextView comments = (TextView)findViewById(R.id.devo_comments);
        comments1 = comments.getText().toString();
        final String devo_id = Devotional.devotional_id;



        if (comments1.equals("") || comments1.length()<4)
        {
            progressDialog.hide();
            Toast.makeText(this, "Please enter a valid comment", Toast.LENGTH_LONG).show();
        }
        else {
            connectUrl = "http://www.samuelagbede.com/coza_social/insert_devotional_comments.php";


            StringRequest jor = new StringRequest(Request.Method.POST, connectUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("Comments Inserted", response);
                    progressDialog.hide();
                    Toast.makeText(Featured.this, "Comments entered successfully", Toast.LENGTH_LONG).show();
                    comments.setText(" ");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error", error.toString());
                }
            }){

                @Override
                protected Map<String, String> getParams()  {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username1 );
                    params.put("comments", comments1);
                    params.put("email_add", email_address1 );
                    params.put("devotional",devo_id);

                    return params;
                }

            };
            Singleton.getInstance(this).addToRequestQueue(jor);
        }
    }


    public void pic_update(View view){
       final CharSequence[] items = {"Wanna Selfie?","Pick a picture","Cancel"};
        AlertDialog.Builder alertD = new AlertDialog.Builder(this);
        alertD.setTitle("Add Picture");
        alertD.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Wanna Selfie?")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, SELFIE);
                    }
                else if (items[which].equals("Pick a picture")){
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }
    else if (items[which].equals("Cancel")){
    dialog.dismiss();
}}});
        alertD.show();
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELFIE){
            if (resultCode == RESULT_OK){

                Uri snappedUri = data.getData();
                //imagePath = getPath(snappedUri);
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap)extras.get("data");
              //  Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
               imgView.setImageBitmap(bitmap);
            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Camera Action Canceled. Please try again", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }





public void start() {
        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.theFrame, new Devotional());
        ft.commit();
        }
public void startMe(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.theFrame, new Editprofile()).commit();
        dl.closeDrawer(nav_drawer);
    }



    private void action_man() {
        nav_drawer = (ListView) findViewById(R.id.list_view_nav_drawer);
        String[] list = {"Devotional", "Publications", "Praise Reports","Notifications", "About", "Log Out"};
        content = new FeaturedAdapterListNav(this, pictures,list);
        View v = getLayoutInflater().inflate(R.layout.header, null);
        nav_drawer.addHeaderView(v);
        nav_drawer.setAdapter(content);

        name  = (TextView)v.findViewById(R.id.usernameprofile);
        emailadd = (TextView)v.findViewById(R.id.emailaddressprofile);
        imgView = (ImageView)v.findViewById(R.id.profilepicturea);
        name.setText(username1);
        emailadd.setText(email_address1);
        Picasso.with(this).load("http://www.samuelagbede.com/coza_social/pictures/ic_launcher.png").into(imgView);

        nav_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayFragment(position);
            }
        });
    }
    public void displayFragment(int position) {
        Fragment fragment = null;
        switch (position) {

            case 1:
                fragment = new Devotional();
                break;
            case 2:
              //  fragment = new PublicationsView();
                startActivity(new Intent(this, PublicationsView.class));
                break;
            case 3:
                startActivity(new Intent(this, PraiseReports.class));

                break;
            case 4:
                fragment = new Notifications();
                break;
            case 5:
                fragment = new About();
                break;
            case 6:
                startActivity(new Intent(this, Login.class));
                sharedPrefs();
                break;
            default:
                Toast.makeText(this, "You have not clicked something within the scope", Toast.LENGTH_LONG).show();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.theFrame, fragment).commit();
            dl.closeDrawer(nav_drawer);
        }
    }

    private void sharedPrefs() {
        editor.clear();
        editor.commit();


    }

    public void update_profile(View view) {
        progressDialog.setMessage("Loading");
        progressDialog.show();
        phone_number = (TextView) findViewById(R.id.phone_number_update);
        location = (TextView) findViewById(R.id.location);
        phone_number1 = phone_number.getText().toString();
        location1 = location.getText().toString();

        if (phone_number1.length() < 10 || location1.length() < 3) {
            Toast.makeText(this, "Please ensure you have inserted all your details", Toast.LENGTH_LONG).show();
        } else {
            try {
                StringRequest theReq = new StringRequest(Request.Method.POST,
                        "http://www.samuelagbede.com/coza_social/update_profile_details.php",
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)  {
                        progressDialog.hide();
                        Toast.makeText(Featured.this, "Successfully updated", Toast.LENGTH_LONG).show();
                        phone_number.setText("");
                        location.setText("");


                    }
                }, new Response.ErrorListener() {
                    @Override
                     public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(Featured.this, "Not updated. Check your internet connection please", Toast.LENGTH_LONG).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", username1 );
                        params.put("phone_number", phone_number1);
                        params.put("location", location1 );


                        return params;
                    }


                };
                Singleton.getInstance(this).addToRequestQueue(theReq);
   }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}