package com.example.cozasocial;


import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by SamuelAgbede on 9/6/2015.
 */
public class PublicationsView extends ActionBarActivity {
    ViewPager publications;
    ViewPagerPublications vp;
    Toolbar toolbar;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.view_publications);
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setLogo(getResources().getDrawable(R.drawable.ic_launcher));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PublicationsView.this, Featured.class));
            }
        });
        setSupportActionBar(toolbar);
        publications = (ViewPager)findViewById(R.id.viewpager_publications);
        vp = new ViewPagerPublications(getSupportFragmentManager());
        publications.setAdapter(vp);

        PagerSlidingTabStrip strip = (PagerSlidingTabStrip)findViewById(R.id.theslidingtabs_publications);

        strip.setViewPager(publications);
    }
}
