package com.example.body_shaper_app.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.body_shaper_app.BodyShaperArrayAdapter;
import com.example.body_shaper_app.Constants;
import com.example.body_shaper_app.R;
import com.example.body_shaper_app.models.Business;
import com.example.body_shaper_app.models.Category;
import com.example.body_shaper_app.models.YelpBusinessesSearchResponse;
import com.example.body_shaper_app.network.YelpApi;
import com.example.body_shaper_app.network.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {
//    private SharedPreferences mSharedPreferences;
//    private String mRecentAddress;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private ListView ListView;
    public List<Business> gyms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ListView = (ListView) findViewById(R.id.listView);
        ButterKnife.bind(this);


        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       Intent intent = new Intent(AboutUsActivity.this, AboutUsDetailActivity.class);
                String sport = ((TextView)view).getText().toString();
                Toast.makeText(AboutUsActivity.this, sport, Toast.LENGTH_LONG).show();
                startActivity(intent);

            }


        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getgyms(location);


//            here we are creating a client object and using it to make a request to the Yelp API

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getgyms(location, "gyms");

        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {

                    List<Business> restaurantsList = response.body().getBusinesses();
                    String[] gyms = new String[restaurantsList.size()];
                    String[] categories = new String[restaurantsList.size()];

                    for (int i = 0; i < gyms.length; i++){
                        gyms[i] = restaurantsList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = restaurantsList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    BodyShaperArrayAdapter adapter = new BodyShaperArrayAdapter(AboutUsActivity.this, android.R.layout.simple_list_item_1,gyms, categories);
                    ListView.setAdapter(adapter);
                    showGyms();

                }
                else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        if (mRecentAddress != null) {
//            getgyms(mRecentAddress);
//        }

    }




    private void getgyms(String mRecentAddress) {

    }







    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showGyms() {
        ListView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


}
