package com.example.cozasocial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by SamuelAgbede on 9/6/2015.
 */
public class ViewPagerPublications extends FragmentPagerAdapter {
    private String tabNames[] = {"Write Publication","Publication of the day"};
    Fragment frag;
    public ViewPagerPublications(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position)
        {
            case 0 : frag = new PublicationsRegister();
                break;
            case 1 :  frag = new Publications();
                break;
            default : frag = new Publications();



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
