package com.trantuandung.technictest.view.viewholder;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
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
        bookItemThumbnail.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        bookItemThumbnail.setColorFilter(Color.argb(50, 0, 0, 0));
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        break;
                    case MotionEvent.ACTION_UP:
                        bookItemThumbnail.setColorFilter(Color.argb(0, 0, 0, 0));
                        break;
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_CANCEL:
                        if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
                            bookItemThumbnail.setColorFilter(Color.argb(0, 0, 0, 0));
                        }
                        break;
                }
                return false;
            }
        });
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
