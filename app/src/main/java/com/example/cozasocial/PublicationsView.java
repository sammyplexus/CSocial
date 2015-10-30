package com.example.cozasocial;


import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.support.v4.view.ViewPager;



import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by SamuelAgbede on 9/6/2015.
 */
public class PublicationsView extends FragmentActivity {
    ViewPager publications;
    ViewPagerPublications vp;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.view_publications);
        publications = (ViewPager)findViewById(R.id.viewpager_publications);
        vp = new ViewPagerPublications(getSupportFragmentManager());
        publications.setAdapter(vp);

        PagerSlidingTabStrip strip = (PagerSlidingTabStrip)findViewById(R.id.theslidingtabs_publications);

        strip.setViewPager(publications);
    }
}
