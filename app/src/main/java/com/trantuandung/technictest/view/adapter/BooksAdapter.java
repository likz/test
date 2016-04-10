package com.trantuandung.technictest.view.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.trantuandung.technictest.model.Book;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<ViewHolder>{
    private final String TAG = BooksAdapter.class.getName();

    protected final int EMPTY_VIEW = 20;
    protected List<Book> bookList;
    protected Resources resources;
    protected Context context;

    public BooksAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }


    @Override
    public int getItemCount() {
        return (bookList == null || bookList.size() == 0) ? 1 : bookList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return (bookList == null || bookList.size() == 0) ? EMPTY_VIEW : super.getItemViewType(position);
    }
}

