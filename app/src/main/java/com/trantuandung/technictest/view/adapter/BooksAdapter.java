package com.trantuandung.technictest.view.adapter;

import android.content.Context;
import android.content.res.Resources;
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
import com.trantuandung.technictest.view.viewholder.ViewHolderBookAdapter;

import java.util.List;

public class BooksAdapter  extends RecyclerView.Adapter<ViewHolder>{
    private final String TAG = BooksAdapter.class.getName();

    protected final int EMPTY_VIEW = 20;
    protected List<Book> bookList;
    protected Resources resources;
    protected Context context;
    BookListener bookListener;

    public BooksAdapter(BookListener bookListener, List<Book> bookList) {
        this.bookListener = bookListener;
        this.bookList = bookList;
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
            return new ViewHolderBookAdapter(LayoutInflater.from(context).inflate(R.layout.book_item_cardview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ViewHolderBookAdapter) {
            final ViewHolderBookAdapter viewHolderBookAdapter = (ViewHolderBookAdapter) holder;
            if (bookList != null) {
                final Book book = bookList.get(position);
                if (book != null) {
                    viewHolderBookAdapter.getBookItemTitle().setText(book.getTitle());
                    viewHolderBookAdapter.getBookItemPrice().setText(String.format(resources.getString(R.string.book_price), book.getPrice()));

                    //load image's file from server
                    ItemsRequester.loadImageIntoView(context, book.getCover(),
                            viewHolderBookAdapter.getBookItemThumbnail());

                    viewHolderBookAdapter.getBookItemAdd().setOnClickListener(new View.OnClickListener() {
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

    @Override
    public int getItemCount() {
        return (bookList == null || bookList.size() == 0) ? 1 : bookList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (bookList == null || bookList.size() == 0) ? EMPTY_VIEW : super.getItemViewType(position);
    }

    public void setItemList(List<Book> itemList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }
}

