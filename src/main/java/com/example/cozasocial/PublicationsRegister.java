package com.example.cozasocial;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SamuelAgbede on 9/6/2015.
 */
public class PublicationsRegister extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading");



        final View view = inflater.inflate(R.layout.write_publications, container, false);
        Button submit_publication = (Button) view.findViewById(R.id.submit_publications);
        submit_publication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView full_name = (TextView)view.findViewById(R.id.publication_fullname_text);
                final TextView email_address = (TextView)view.findViewById(R.id.publication_email_address);
                final TextView title = (TextView)view.findViewById(R.id.publication_title);
                final TextView article = (TextView)view.findViewById(R.id.publication_text);

                final String _full_name = full_name.getText().toString();
                final String _email_address = email_address.getText().toString();
                final String _title = title.getText().toString();
                final String _article = article.getText().toString();

                if (_full_name.equals("")|| _email_address.equals("")|| _title.equals("")||_article.equals(""))
                {
                    Toast.makeText(getActivity(),"Please ensure you have completed everything", Toast.LENGTH_LONG).show();

                }
                else {
                    progressDialog.show();
                    String url = "http://www.samuelagbede.com/coza_social/insert_publications.php";


                    StringRequest jor1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            progressDialog.hide();
                            Toast.makeText(getActivity(), "Publication entered. We'll review and update accordingly. We celebrate you", Toast.LENGTH_LONG).show();
                            full_name.setText("");
                            email_address.setText("");
                            article.setText("");
                            title.setText("");

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                             progressDialog.hide();
                            Toast.makeText(getActivity(), "Something's up. Please try again", Toast.LENGTH_LONG).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("topic", _title);
                            params.put("username_writer", _full_name);
                            params.put("content", _article);
                            params.put("email_address", _email_address);

                            return params;
                        }


                    };
                    Singleton.getInstance(getActivity()).addToRequestQueue(jor1);
                }
            }
        });

        return view;
    }
}
