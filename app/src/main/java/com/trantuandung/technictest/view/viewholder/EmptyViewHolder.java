package com.trantuandung.technictest.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.trantuandung.technictest.R;

public class EmptyViewHolder extends RecyclerView.ViewHolder {

    private TextView recycleview_empty_msg;

    public EmptyViewHolder(View itemView) {
	super(itemView);
        recycleview_empty_msg = (TextView) itemView.findViewById(R.id.recycleview_empty_msg);
    }

    public TextView getEmptyMessaggeView() {
        return recycleview_empty_msg;
    }
}
