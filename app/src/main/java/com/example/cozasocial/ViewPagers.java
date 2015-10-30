package com.example.cozasocial;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

import static android.support.v4.app.ActivityCompat.startActivity;


/**
 * Created by SamuelAgbede on 6/26/2015.
 */
public class ViewPagers extends FragmentPagerAdapter {
    private String tabNames[] = {"Register","Praise Reports"};
    Fragment frag;
    public ViewPagers(FragmentManager fm) {
        super(fm);
    }

    @Override

    public Fragment getItem(int position) {
        switch (position){
            case 0 : frag = new PraiseReportsRegister();
            break;
            case 1 : frag = new PraiseReportsView();
            break;

            default : new PraiseReportsRegister();


        }
    return frag;

    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}
