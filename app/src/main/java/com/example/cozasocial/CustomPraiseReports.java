package com.example.cozasocial;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamuelAgbede on 9/8/2015.
 */
public class CustomPraiseReports extends BaseAdapter {
    private List<PraiseReportsModel> praiseModel ;
    private Activity activity;
    LayoutInflater inflater;
    public CustomPraiseReports(List<PraiseReportsModel> praiseModel, Activity activity) {
        this.praiseModel = praiseModel;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return praiseModel.size();
    }

    @Override
    public Object getItem(int position) {
        return praiseModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.praise_report_individual, null);

        TextView praise_title = (TextView)convertView.findViewById(R.id.praise_title);
        TextView praise_details = (TextView)convertView.findViewById(R.id.praise_details);
        TextView full_name = (TextView)convertView.findViewById(R.id.praise_report_full_name);

        PraiseReportsModel praiseReportsModel = praiseModel.get(position);

        praise_title.setText(praiseReportsModel.getTitle());
        praise_details.setText(praiseReportsModel.getPraise_report());
        full_name.setText(praiseReportsModel.getFull_name());

        return convertView;
    }
}
