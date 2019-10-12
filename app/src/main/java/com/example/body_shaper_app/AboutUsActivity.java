package com.example.body_shaper_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {
    private TextView LocationTextView;
    private ListView ListView;
    private String[] sports = new String[] {"HERE ARE SOME SPORT",
            "ENDOMORPH:",
            "Instead of trying to jog consider",
            "*race walking, *swimming,* rowing and cycling.*Strength training.",
            "MESOMORPH:",
            "*excel at weightlifting, *bodybuilding and power sports",
            "ECTOMORPH:",
            "*swimming, *soccer, *long distance running, *marathon running",
            "triathlons and cycling, *basketball, *tennis and gymnastics."};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ListView = (ListView) findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sports);
        ListView.setAdapter(adapter);


        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sport = ((TextView)view).getText().toString();
                Toast.makeText(AboutUsActivity.this, sport, Toast.LENGTH_LONG).show();
            }
        });

    }
}
