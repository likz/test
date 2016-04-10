package com.trantuandung.technictest.view.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.trantuandung.technictest.R;

public class ViewHolderBookCatalogAdapter extends ViewHolderBookItem {
    private ImageView bookItemAdd;
    public ViewHolderBookCatalogAdapter(View itemView) {
        super(itemView);
    }

    @Override
    protected void addButton(){
        bookItemAdd = (ImageView) itemView.findViewById(R.id.bookItem_add);
    }

    public ImageView getBookItemAdd() {
        return bookItemAdd;
    }
}
