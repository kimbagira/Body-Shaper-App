//package com.example.body_shaper_app;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//public class AboutUsArrayAdapter extends BaseAdapter {
//
//
//    private Context mContext;
//    private String[] aboutus;
//
//    public AboutUsArrayAdapter(Context mContext, String[] aboutus) {
//        this.mContext = mContext;
//        this.aboutus = aboutus;
//    }
//
//    @Override
//    public int getCount() {
//        return aboutus.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View gridView;
//
//        // get layout from xml file
//        ListView = inflater.inflate(R.layout.listView1, null);
//        // pull views
//        TextView letterView = (TextView) gridView
//                .findViewById(R.id.listView1);
//        // set values into views
//        letterView.setText(aboutus[position]);
//
//
//    }else {
//        ListView = (View) convertView;
//    }
//        return ListView;
//    }

