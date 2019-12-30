package com.selfvsself.moviewatch.Model.Helper;

import androidx.recyclerview.widget.RecyclerView;

public interface RecyclerItemTouchHelperListener {

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
