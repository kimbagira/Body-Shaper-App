package com.example.body_shaper_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.body_shaper_app.R;
import com.example.body_shaper_app.models.Gym;
import com.example.body_shaper_app.ui.AboutUsDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsListAdapter extends RecyclerView.Adapter<AboutUsListAdapter.AboutUsViewHolder>{
    private ArrayList<Gym> mGym = new ArrayList<>();
    private Context mContext;

    public AboutUsListAdapter(Context context, ArrayList<Gym> gyms){
        mContext = context;
        mGym = gyms;
    }

    @Override
    public AboutUsListAdapter.AboutUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutus_list_item, parent, false);
        AboutUsViewHolder viewHolder = new AboutUsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AboutUsListAdapter.AboutUsViewHolder holder, int position){
        holder.bindGym(mGym.get(position));
    }

    @Override
    public int getItemCount(){
        return mGym.size();
    }

    public class AboutUsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.restaurantImageView)
        ImageView mGymImageView;
        @BindView(R.id.restaurantNameTextView)
        TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public AboutUsViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindGym(Gym gym) {
            mNameTextView.setText(gym.getName());
            mCategoryTextView.setText(gym.getCategories().get(0));
            mRatingTextView.setText("Rating: " + gym.getRating() + "/5");
            Picasso.get().load(gym.getImageUrl()).into(mGymImageView);
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, AboutUsDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("gyms", Parcels.wrap(mGym));
            mContext.startActivity(intent);
        }
    }

}
