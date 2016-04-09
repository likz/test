package com.trantuandung.technictest.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trantuandung.technictest.R;

public class ViewHolderBookAdapter extends RecyclerView.ViewHolder {
    private ImageView bookItemThumbnail;
    private TextView bookItemTitle;
    private TextView bookItemPrice;
    public ViewHolderBookAdapter(View itemView) {
        super(itemView);
        bookItemThumbnail = (ImageView) itemView.findViewById(R.id.bookItem_thumbnail);
        bookItemTitle = (TextView) itemView.findViewById(R.id.bookItem_title);
        bookItemPrice = (TextView) itemView.findViewById(R.id.bookItem_price);
    }

    public ImageView getBookItemThumbnail() {
        return bookItemThumbnail;
    }

    public TextView getBookItemTitle() {
        return bookItemTitle;
    }

    public TextView getBookItemPrice() {
        return bookItemPrice;
    }
}
