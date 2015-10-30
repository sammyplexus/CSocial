package com.example.cozasocial;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;
public class FeaturedAdapterListNav extends ArrayAdapter<String> {
    Activity context;

    Integer[] picture;
    String[] content;

    public FeaturedAdapterListNav(Activity context, Integer[] picture, String[] content) {
        super(context, R.layout.single_line_xml, content);
        this.context = context;

        this.picture = picture;
        this.content = content;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();
        View theView = inflater.inflate(R.layout.single_line_xml, null);

        ImageView image = (ImageView) theView.findViewById(R.id.imag);
        TextView contents = (TextView) theView.findViewById(R.id.tex);


        image.setImageResource(picture[position]);
        contents.setText(content[position]);

            return theView;
    }
}
