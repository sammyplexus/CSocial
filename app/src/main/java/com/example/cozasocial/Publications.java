package com.example.cozasocial;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamuelAgbede on 5/14/2015.
 */
public class Publications extends Fragment {
ArrayList<JSONObject> array = new ArrayList<JSONObject>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_publications_expanded, container, false);




        String url = "http://www.samuelagbede.com/coza_social/publications.php";

        JsonArrayRequest jare = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) throws JSONException {
                Log.d("Result of publica", response.toString());
               // JSONObject jobj = response.getJSONObject(0);

                for(int i = 0; i < 10; i++){
                    array.add(response.getJSONObject(i));
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Singleton.getInstance(getActivity()).addToRequestQueue(jare);


        return v;
    }
}
