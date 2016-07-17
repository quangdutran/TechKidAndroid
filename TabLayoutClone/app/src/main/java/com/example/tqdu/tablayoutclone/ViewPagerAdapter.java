package com.example.tqdu.tablayoutclone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tqdu on 7/16/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static int NUMBER_PAGE = 4;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        KeyBoardFragment keyBoardFragment = new KeyBoardFragment();
        switch (position) {
            case 0:
                return keyBoardFragment;
            case 1:
                return keyBoardFragment;
            case 2:
                return keyBoardFragment;
            case 3:
                return keyBoardFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_PAGE;
    }
}
