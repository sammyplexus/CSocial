package com.example.cozasocial;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SamuelAgbede on 7/13/2015.
 */
public class PraiseReportsRegister extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


       view  = inflater.inflate(R.layout.praisereportsregister, container, false);
        Button submit_praise_reports =  (Button)view.findViewById(R.id.submit_praise_reports);
        submit_praise_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView full_name = (TextView) view.findViewById(R.id.praise_fullname_text);
                final TextView praise_report_title = (TextView) view.findViewById(R.id.praise_report_title);
                final TextView praise_report = (TextView) view.findViewById(R.id.praise_reports_text);
                final TextView email_addresss = (TextView) view.findViewById(R.id.praise_report_email_address);

                final String title = praise_report_title.getText().toString();
                final String _full = full_name.getText().toString();
                final String praise_report1 = praise_report.getText().toString();
                final String email_address1 = email_addresss.getText().toString();

                Log.d("Title and full name", title + " " + _full);

                if (_full.equals("") || title.equals("") || praise_report1.equals("") || email_address1.equals("")) {
                    Toast.makeText(getActivity(), "Please enter your full details", Toast.LENGTH_LONG).show();

                } else {
                    progressDialog.setMessage("Loading");
                    progressDialog.show();
                    String url = "http://www.samuelagbede.com/coza_social/praise_reports.php";


                    StringRequest jor1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        progressDialog.hide();
                            Toast.makeText(getActivity(),"Praise Report entered. We'll review and update accordingly. We celebrate you", Toast.LENGTH_LONG).show();
                            full_name.setText("");
                            email_addresss.setText("");
                            praise_report.setText("");
                            praise_report_title.setText("");

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error", error.toString());
                            progressDialog.hide();
                            Toast.makeText(getActivity(),"Something's up. Please try again", Toast.LENGTH_LONG).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email_add", email_address1 );
                            params.put("title", title);
                            params.put("praise_reports", praise_report1 );
                            params.put("full_name", _full );

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
