
package com.example.body_shaper_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.body_shaper_app.R;

public class MainActivity extends AppCompatActivity {
    private Button mLoginButton;
    private EditText mLocationEditText3;
    private Button mSavedgymsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLocationEditText3=(EditText) findViewById(R.id.locationEditText3);
        mSavedgymsButton=(Button) findViewById(R.id.savedgymsButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String location = mLocationEditText3.getText().toString();
                Intent intent = new Intent(MainActivity.this , AboutUsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);

            }
        });


        mSavedgymsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , SavedGymListActivity.class);
                startActivity(intent);

            }
        });

    }
}