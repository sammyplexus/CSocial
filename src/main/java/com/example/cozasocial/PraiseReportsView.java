package com.example.cozasocial;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamuelAgbede on 7/13/2015.
 */
public class PraiseReportsView extends Fragment
{
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        final AlertDialog.Builder alertD = new AlertDialog.Builder(getActivity());
      final List<PraiseReportsModel> prm = new ArrayList<PraiseReportsModel>();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        v = inflater.inflate(R.layout.praisereportsview, container, false);
        ListView  listView = (ListView)v.findViewById(R.id.listview_praisereports);
        final CustomPraiseReports customPraiseReports = new CustomPraiseReports(prm, getActivity());
        listView.setAdapter(customPraiseReports);

        final ArrayList<JSONObject>the_value = new ArrayList<JSONObject>();

       JsonArrayRequest jare = new JsonArrayRequest("http://www.samuelagbede.com/coza_social/get_praise_reports.php",
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) throws JSONException{
                        progressDialog.hide();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = response.getJSONObject(i);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            PraiseReportsModel praiseReportsModel = new PraiseReportsModel();

                            praiseReportsModel.setTitle(jsonObject.getString("title"));
                            praiseReportsModel.setFull_name(jsonObject.getString("full_name"));
                            praiseReportsModel.setPraise_report(jsonObject.getString("praise_report"));

                            prm.add(praiseReportsModel);
                        }
                        customPraiseReports.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener()
        {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.hide();
                        Toast.makeText(getActivity(), "Something's not right. Please check your internet connection and try again", Toast.LENGTH_LONG).show();
                    }


    });

        Singleton.getInstance(getActivity()).addToRequestQueue(jare);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertD.setTitle("Praise Report");
                alertD.setMessage(prm.get(position).getPraise_report());
                alertD.show();


            }
        });



        return v;
    }

    }

