package com.example.body_shaper_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.body_shaper_app.R;
import com.example.body_shaper_app.models.Gym;

import com.example.body_shaper_app.ui.AboutUsDetailFragment;
import com.example.body_shaper_app.ui.SavedGymListActivity;
import com.example.body_shaper_app.util.ItemTouchHelperAdapter;
import com.example.body_shaper_app.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseAboutUsListAdapter extends FirebaseRecyclerAdapter<Gym, FirebaseAboutUsViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Gym> mgyms = new ArrayList<>();


    public FirebaseAboutUsListAdapter(FirebaseRecyclerOptions<Gym> options, DatabaseReference ref, OnStartDragListener onStartDragListener, Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mgyms.add(dataSnapshot.getValue(Gym.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseAboutUsViewHolder FirebaseAboutUsViewHolder, int position, @NonNull Gym gym) {
        FirebaseAboutUsViewHolder.bindGym(gym);
        FirebaseAboutUsViewHolder.mgymImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(FirebaseAboutUsViewHolder);
                }
                return false;
            }
        });

         FirebaseAboutUsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, AboutUsDetailFragment.class);
                        intent.putExtra("position", FirebaseAboutUsViewHolder.getAdapterPosition());
                        intent.putExtra("gyms", Parcels.wrap(mgyms));
                        mContext.startActivity(intent);
                    }
                });

    }

    @NonNull
    @Override
    public FirebaseAboutUsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutus_list_item_drag, parent, false);
        return new FirebaseAboutUsViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        Collections.swap(mgyms, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInForebase();
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        mgyms.remove(position);
        getRef(position).removeValue();
    }

    public void startListening() {
    }

      private void setIndexInForebase(){
           for(Gym gym: mgyms){
                 int index = mgyms.indexOf(gym);
                 DatabaseReference mReference = getRef(index);
                 gym.setIndex(Integer.toString(index));
                 mReference.setValue(gym);
             }
         }

         @Override
         public void stopListening(){
             super.stopListening();
             mRef.removeEventListener(mChildEventListener);
         }



















}


