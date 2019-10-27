package com.example.body_shaper_app.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.body_shaper_app.R;
import com.example.body_shaper_app.models.Business;
import com.example.body_shaper_app.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutUsDetailFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.gymImageView) ImageView mImageLabel;
    @BindView(R.id.gymNameTextView) TextView mNameLabel;
    @BindView(R.id.cuisineTextView) TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.savegymButton) TextView msavegymButton;

    private Business mgym;
    //    newInstance(), is used instead of a constructor and returns a new instance of our AboutUsDetailFragment
    public static AboutUsDetailFragment newInstance(Business mgym) {
        AboutUsDetailFragment AboutUsDetailFragment = new AboutUsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("mgym", Parcels.wrap(mgym));
        AboutUsDetailFragment.setArguments(args);
        return AboutUsDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mgym = Parcels.unwrap(getArguments().getParcelable("gym"));
    }
    //this AboutUs object is then used to set our ImageView and TextViews.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mgym.getImageUrl()).into(mImageLabel);

        List<String> categories = new ArrayList<>();

        for (Category category : mgym.getCategories()) {
            categories.add(category.getTitle());
        }


        mNameLabel.setText(mgym.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
        mRatingLabel.setText(Double.toString(mgym.getRating()) + "/5");
        mPhoneLabel.setText(mgym.getPhone());
        mAddressLabel.setText(mgym.getLocation().toString());

        //for the implicity intent
        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;

    }
    //for the implicity intent
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mgym.getUrl()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mgym.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mgym.getCoordinates().getLatitude()
                            + "," + mgym.getCoordinates().getLongitude()
                            + "?q=(" + mgym.getName() + ")"));
            startActivity(mapIntent);
        }

    }
}