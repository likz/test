package com.trantuandung.technictest.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trantuandung.technictest.R;
import com.trantuandung.technictest.listener.BookListener;
import com.trantuandung.technictest.model.Book;
import com.trantuandung.technictest.server.ItemsRequester;
import com.trantuandung.technictest.view.viewholder.EmptyViewHolder;
import com.trantuandung.technictest.view.viewholder.ViewHolderBookCatalogAdapter;

import java.util.List;

public class BooksCatalogAdapter extends BooksAdapter{
    private final String TAG = BooksCatalogAdapter.class.getName();

    private BookListener bookListener;

    public BooksCatalogAdapter(BookListener bookListener, List<Book> bookList) {
        super(bookList);
        this.bookListener = bookListener;
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
            return new ViewHolderBookCatalogAdapter(LayoutInflater.from(context).inflate(R.layout.book_catalog_cardview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ViewHolderBookCatalogAdapter) {
            final ViewHolderBookCatalogAdapter viewHolderBookCatalogAdapter = (ViewHolderBookCatalogAdapter) holder;
            if (bookList != null) {
                final Book book = bookList.get(position);
                if (book != null) {
                    viewHolderBookCatalogAdapter.getBookItemTitle().setText(book.getTitle());
                    viewHolderBookCatalogAdapter.getBookItemPrice().setText(String.format(resources.getString(R.string.book_price), book.getPrice()));

                    //load image's file from server
                    ItemsRequester.loadImageIntoView(context, book.getCover(),
                            viewHolderBookCatalogAdapter.getBookItemThumbnail());

                    viewHolderBookCatalogAdapter.getBookItemAdd().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "add", Toast.LENGTH_SHORT).show();
                            bookListener.addBook(book);
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

