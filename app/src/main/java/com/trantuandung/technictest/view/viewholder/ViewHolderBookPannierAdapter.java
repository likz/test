package com.trantuandung.technictest.view.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.trantuandung.technictest.R;

public class ViewHolderBookPannierAdapter extends ViewHolderBookItem{
    private ImageView bookItemDelete;

    public ViewHolderBookPannierAdapter(View itemView) {
        super(itemView);
    }

    @Override
    protected void addButton(){
        bookItemDelete = (ImageView) itemView.findViewById(R.id.bookItem_delete);
    }

    public ImageView getBookItemDelete() {
        return bookItemDelete;
    }
}
