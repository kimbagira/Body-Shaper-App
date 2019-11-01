package com.example.body_shaper_app.util;

public interface ItemTouchHelperAdapter {
//    onItemMove() will be called each time the user moves an item by dragging it across the touch screen
    boolean onItemMove(int fromPosition, int toPosition);
//    onItemDismiss() is called when an item has been dismissed with a swipe motion.
    void onItemDismiss(int position);
}
