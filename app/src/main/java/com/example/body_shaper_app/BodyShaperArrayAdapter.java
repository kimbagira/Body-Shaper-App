
package com.example.body_shaper_app;

import android.content.Context;
import android.widget.ArrayAdapter;

public class BodyShaperArrayAdapter  extends ArrayAdapter {
    private Context mContext;
    private String[] mGym;
    private String[] mSport;


    public BodyShaperArrayAdapter(Context mContext, int resource, String[] mGym, String[] mSport) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mGym = mGym;
        this.mSport = mSport;
    }

    @Override
    public Object getItem(int position) {
        String gym = mGym[position];
        String sport = mSport[position];
        return String.format("%s \nServes great: %s", gym, sport);

    }

    @Override
    public int getCount() {
        return mGym.length;
    }
}