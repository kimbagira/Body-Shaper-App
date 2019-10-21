package com.example.body_shaper_app.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.body_shaper_app.models.Business;
import com.example.body_shaper_app.ui.AboutUsDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class AboutUsPageAdapter extends FragmentPagerAdapter {
    private List<Business> mgym;
    public AboutUsPageAdapter(FragmentManager fm, int behavior, List<Business>gym) {
super(fm);
        mgym =gym;
     }

    @Override
    public Fragment getItem(int position) {
        return AboutUsDetailFragment.newInstance( mgym.get(position));
    }

    @Override
    public int getCount() {
        return  mgym.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  mgym.get(position).getName();
    }

}
