package com.example.cozasocial;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;


/**
 * Created by SamuelAgbede on 6/17/2015.
 */
public class PraiseReports extends ActionBarActivity {


    ViewPager vpa;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
       toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setLogo(getResources().getDrawable(R.drawable.ic_launcher));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PraiseReports.this, Featured.class));
            }
        });
        setSupportActionBar(toolbar);
        vpa = (ViewPager)findViewById(R.id.viewpager);
        ViewPagers vp = new ViewPagers(getSupportFragmentManager());

        vpa.setAdapter(vp);

        PagerSlidingTabStrip strip = (PagerSlidingTabStrip)findViewById(R.id.theslidingtabs);

        strip.setViewPager(vpa);

    }
}
