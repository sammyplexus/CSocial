package com.example.cozasocial;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by SamuelAgbede on 5/14/2015.
 */
public class Notifications extends Fragment {
    private List<NotificationsModel> nm = new ArrayList<NotificationsModel>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final AlertDialog.Builder alertD = new AlertDialog.Builder(getActivity());
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        final View v = inflater.inflate(R.layout.notifications, container, false);
        ListView listView = (ListView)v.findViewById(R.id.list_view_notification);
        final GridAdapter adapter = new GridAdapter(getActivity(), nm);
        listView.setAdapter(adapter);

        JsonArrayRequest jare = new JsonArrayRequest("http://www.samuelagbede.com/coza_social/notifications.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) throws JSONException {
                progressDialog.hide();
                Log.d("Value of jsonarray", response.toString());
                for(int i = 0; i < response.length(); i++)
                {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);
                        Log.d("Value of jsonobject", jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                    NotificationsModel notificationsModel = new NotificationsModel();
                    notificationsModel.setStatus("D");
                    notificationsModel.setContent(jsonObject.getString("content"));
                    notificationsModel.setTitle(jsonObject.getString("title"));
                    Log.d("Value of time", jsonObject.getString("date_time"));
                    notificationsModel.setTime(jsonObject.getString("date_time"));

                    nm.add(notificationsModel);


                }

                adapter.notifyDataSetChanged();
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getActivity(), "there is an error "+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("There is an error", error.toString());
            }
        });

        Singleton.getInstance(getActivity()).addToRequestQueue(jare);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertD.setTitle(nm.get(position).getTitle());
                alertD.setMessage(nm.get(position).getContent());
                alertD.show();


            }
        });




                return v;
    }
}
