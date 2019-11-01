package com.example.body_shaper_app.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.body_shaper_app.R;
import com.example.body_shaper_app.models.Gym;
import com.squareup.picasso.Picasso;

class FirebaseGymViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;
    public ImageView mgymImageView;

    public FirebaseGymViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener((View.OnClickListener) this);
    }

    public void bindGym(Gym gym){
        mgymImageView = mView.findViewById(R.id.restaurantImageView);
        TextView nameTextView = mView.findViewById(R.id.restaurantNameTextView);
        TextView categoryTextView = mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = mView.findViewById(R.id.ratingTextView);

        nameTextView.setText(gym.getName());
        categoryTextView.setText(gym.getCategories().get(0));
        ratingTextView.setText("Rating: " + gym.getRating() + "/5");
        Picasso.get().load(gym.getImageUrl()).into(mgymImageView);
    }

    public void bindRestaurant(Gym gym){
        mgymImageView = mView.findViewById(R.id.gymImageView);
        TextView nameTextView = mView.findViewById(R.id.gymNameTextViewName);
        TextView categoryTextView = mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = mView.findViewById(R.id.ratingTextView);

        nameTextView.setText(gym.getName());
        categoryTextView.setText(gym.getCategories().get(0));
        ratingTextView.setText("Rating: " + gym.getRating() + "/5");
        Picasso.get().load(gym.getImageUrl()).into(mgymImageView);
    }












}
