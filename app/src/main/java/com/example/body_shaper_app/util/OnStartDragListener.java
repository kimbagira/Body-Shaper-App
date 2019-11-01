package com.example.body_shaper_app.util;

import androidx.recyclerview.widget.RecyclerView;

public interface OnStartDragListener {
//    onStartDrag() will be called when the user begins a 'drag-and-drop' interaction with the touchscreen.
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
