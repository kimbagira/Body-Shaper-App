package com.example.body_shaper_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.body_shaper_app.Constants;
import com.example.body_shaper_app.R;
import com.example.body_shaper_app.models.Gym;
import com.example.body_shaper_app.ui.AboutUsDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseAboutUsViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;
    public ImageView mgymImageView;

    public FirebaseAboutUsViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindGym(Gym gym){
        mgymImageView = mView.findViewById(R.id.gymImageView);
        TextView nameTextView = mView.findViewById(R.id.gymNameTextView);
        TextView categoryTextView = mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = mView.findViewById(R.id.ratingTextView);

        nameTextView.setText(gym.getName());
        categoryTextView.setText(gym.getCategories().get(0));
        ratingTextView.setText("Rating: " +gym.getRating() + "/5");
        Picasso.get().load(gym.getImageUrl()).into(mgymImageView);
    }




    @Override
    public void onClick(View view){
        final ArrayList<Gym> gyms = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GYMS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    gyms.add(snapshot.getValue(Gym.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, AboutUsDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("gyms", Parcels.wrap(gyms));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );
    }



}
