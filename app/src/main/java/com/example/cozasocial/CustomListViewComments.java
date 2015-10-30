package com.example.cozasocial;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SamuelAgbede on 9/6/2015.
 */
public class CustomListViewComments extends BaseAdapter {
    private Activity activity;
    private List<DevComments> devComments;
    private LayoutInflater inflater;

    public CustomListViewComments(Activity activity, List<DevComments> devComments) {
        this.activity = activity;
        this.devComments = devComments;
    }

    @Override
    public int getCount() {
        return devComments.size();
    }

    @Override
    public Object getItem(int position) {
        return devComments.get(position);
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
            convertView = inflater.inflate(R.layout.individual_comments_listview, null);
        TextView username = (TextView)convertView.findViewById(R.id.username_comments);
        TextView email_address = (TextView)convertView.findViewById(R.id.email_address_comments_devotional);
        TextView comments = (TextView)convertView.findViewById(R.id.comments_devotional);

        DevComments dc = devComments.get(position);
        username.setText(dc.getUsername());
        email_address.setText(dc.getEmail_address());
        comments.setText(dc.getComments());

        return convertView;
    }
}
