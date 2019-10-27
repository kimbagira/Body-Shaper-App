package com.example.body_shaper_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.body_shaper_app.R;
import com.example.body_shaper_app.adapters.AboutUsPageAdapter;
import com.example.body_shaper_app.models.Business;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private AboutUsPageAdapter adapterViewPager;
    ArrayList<Business> mgym = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_detail);
        ButterKnife.bind(this);
        int Fragment = 0;


        mgym = Parcels.unwrap(getIntent().getParcelableExtra("gym"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new AboutUsPageAdapter(getSupportFragmentManager(),Fragment,mgym);
//        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);


    }
}