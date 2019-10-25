package com.example.body_shaper_app.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.body_shaper_app.Constants;
import com.example.body_shaper_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
private DatabaseReference mSearchedLocationReference;

    private Button mLoginButton;
    private EditText mLocationEditText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLocationEditText3 = (EditText) findViewById(R.id.locationEditText3);
        mLoginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (v == mLoginButton) {
                    String location = mLocationEditText3.getText().toString();
                    saveLocationToFirebase(location);
//                    if(!(location).equals("")) {
//                        addToSharedPreferences(location);
//                    }
                    Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }
            }
        });

    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.setValue(location);
    }
//        private void addToSharedPreferences(String location) {
//            mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//        }

}