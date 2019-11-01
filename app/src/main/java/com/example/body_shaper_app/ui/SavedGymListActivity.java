package com.example.body_shaper_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.body_shaper_app.Constants;
import com.example.body_shaper_app.R;
import com.example.body_shaper_app.adapters.FirebaseAboutUsListAdapter;
import com.example.body_shaper_app.models.Gym;
import com.example.body_shaper_app.util.OnStartDragListener;
import com.example.body_shaper_app.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedGymListActivity extends AppCompatActivity {
    private DatabaseReference mGymReference;
    private FirebaseAboutUsListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView1)
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_gym_list);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mGymReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GYMS).child(uid);
        FirebaseRecyclerOptions<Gym> options =
                new FirebaseRecyclerOptions.Builder<Gym>()
                        .setQuery(mGymReference, Gym.class)
                        .build();

        mFirebaseAdapter = new FirebaseAboutUsListAdapter(options, mGymReference, (OnStartDragListener) this, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
    public void onStartDrag(FirebaseGymViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }
}




