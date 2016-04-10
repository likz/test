package com.trantuandung.technictest.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trantuandung.technictest.R;
import com.trantuandung.technictest.listener.AmountListener;
import com.trantuandung.technictest.listener.PannierListener;
import com.trantuandung.technictest.model.Book;
import com.trantuandung.technictest.server.ItemsRequester;
import com.trantuandung.technictest.view.viewholder.EmptyViewHolder;
import com.trantuandung.technictest.view.viewholder.ViewHolderBookPannierAdapter;

import java.util.List;

public class BooksPannierAdapter extends BooksAdapter{
    private final String TAG = BooksPannierAdapter.class.getName();

    private PannierListener pannierListener;
    private AmountListener amountListener;

    public BooksPannierAdapter(AmountListener amountListener, PannierListener pannierListener, List<Book> bookList) {
        super(bookList);
        this.pannierListener = pannierListener;
        this.amountListener = amountListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        resources = context.getResources();

        if (viewType == EMPTY_VIEW) {
            View mView = LayoutInflater.from(context).inflate(R.layout.empty_recycleview, parent, false);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) mView.getLayoutParams();
            params.setMargins(0, parent.getMeasuredHeight() / 2, 0, 0);
            mView.setLayoutParams(params);

            return new EmptyViewHolder(mView);
        } else {
            return new ViewHolderBookPannierAdapter(LayoutInflater.from(context).inflate(R.layout.book_pannier_cardview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderBookPannierAdapter) {
            final ViewHolderBookPannierAdapter viewHolderBookPannierAdapter = (ViewHolderBookPannierAdapter) holder;
            if (bookList != null) {
                final Book book = bookList.get(position);
                if (book != null) {
                    viewHolderBookPannierAdapter.getBookItemTitle().setText(book.getTitle());
                    viewHolderBookPannierAdapter.getBookItemPrice().setText(String.format(resources.getString(R.string.book_price), book.getPrice()));

                    //load image's file from server
                    ItemsRequester.loadImageIntoView(context, book.getCover(),
                            viewHolderBookPannierAdapter.getBookItemThumbnail());

                    viewHolderBookPannierAdapter.getBookItemDelete().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, String.format(resources.getString(R.string.delete_book), book.getTitle()), Toast.LENGTH_SHORT).show();
                            pannierListener.deleteBook(book);
                            amountListener.setCartAmount();
                            bookList = pannierListener.getBookList();
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        } else {
            EmptyViewHolder _viewHolderMessenger = (EmptyViewHolder) holder;
            _viewHolderMessenger.getEmptyMessaggeView().setText(resources.getText(R.string.text_empty_list).toString());
        }
    }
}

