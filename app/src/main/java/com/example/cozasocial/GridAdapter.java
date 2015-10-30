package com.example.cozasocial;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class GridAdapter extends BaseAdapter
{
    TextView notification_status;
    TextView notification;
    TextView notification_title;
   TextView notification_time;
    Context context;
    List<NotificationsModel> notificationsModel;


    public GridAdapter(Context c , List<NotificationsModel> notificationsModel){
            context = c;
            this.notificationsModel = notificationsModel;
    }

    @Override
    public int getCount()
    {
        return notificationsModel.size();
    }

    @Override
    public Object getItem(int position)
    {
    return notificationsModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(convertView == null)
        convertView = layoutInflater.inflate(R.layout.individual_grid, null);

        notification = (TextView)convertView.findViewById(R.id.details_with_excerpts);
        notification_status = (TextView)convertView.findViewById(R.id.notification_status);
        notification_title = (TextView)convertView.findViewById(R.id.notification_title);
        notification_time = (TextView)convertView.findViewById(R.id.time_notification);

        NotificationsModel nm = notificationsModel.get(position);

        notification.setText(nm.getContent());
        notification_status.setText(nm.getStatus());
        notification_title.setText(nm.getTitle());
        notification_time.setText(nm.getTime());
     return convertView;
    }
}

