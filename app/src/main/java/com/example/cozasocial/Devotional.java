package com.example.cozasocial;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SamuelAgbede on 5/14/2015.
 */
public class Devotional extends Fragment {
    public String url;
    static TextView title;
    static TextView writer;
    static TextView content;
    static TextView passage;
    static String devotional_id;
    String yourid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<DevComments> devComments = new ArrayList<DevComments>();
        CustomListViewComments customListViewComments = new CustomListViewComments(getActivity(), devComments);

        View v = inflater.inflate(R.layout.devotional_expanded, container, false);

        title = (TextView) v.findViewById(R.id.devotional_expanded_title);
        writer = (TextView) v.findViewById(R.id.devotional_expanded_writer);
        content = (TextView) v.findViewById(R.id.devotional_expanded_message);
        passage = (TextView) v.findViewById(R.id.devotional_expanded_bible_passage);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Getting devotional for today");
        progressDialog.setMessage("Please hold on a bit");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        ListView comments = (ListView) v.findViewById(R.id.comments_listview);
        comments.setAdapter(customListViewComments);
        String url_comments = "http://www.samuelagbede.com/wordfinder/getDevoComments.php";
        String url = "http://www.samuelagbede.com/coza_social/devotional.php";

        progressDialog.show();

    JsonArrayRequest jare = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) throws JSONException {

                JSONObject jobj = response.getJSONObject(0);
                progressDialog.hide();
                title.append(jobj.getString("title"));
                content.setText(jobj.getString("content"));

                writer.append(jobj.getString("writer"));
                passage.append(jobj.getString("Bible_verse"));
                devotional_id = jobj.getString("_id");
                Log.d("Devotional ID", devotional_id);
                yourid = devotional_id;
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Log.d("Error ", error.toString());

                        Toast.makeText(getActivity(), "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
                    }
                });
        Singleton.getInstance(getActivity()).addToRequestQueue(jare);


        return v;

    }
}


