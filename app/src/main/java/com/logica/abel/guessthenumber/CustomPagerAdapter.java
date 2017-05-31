package com.logica.abel.guessthenumber;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by abel on 2/03/17.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GuessNumberFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
