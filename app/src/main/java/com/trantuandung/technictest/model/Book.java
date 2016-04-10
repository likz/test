package com.trantuandung.technictest.model;

import android.database.Cursor;
import android.text.TextUtils;

import com.trantuandung.technictest.database.BookContract;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private int price;
    private String cover;

    public String getCover() {
        return cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return price == book.getPrice() &&
                !TextUtils.isEmpty(isbn) && !isbn.equals(book.getIsbn()) &&
                !TextUtils.isEmpty(title) && title.equals(book.getTitle()) &&
                !TextUtils.isEmpty(cover) && cover.equals(book.getCover());
    }

    public Book(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(BookContract._ID));
        isbn = cursor.getString(cursor.getColumnIndex(BookContract.COL_ISBN));
        title = cursor.getString(cursor.getColumnIndex(BookContract.COL_TITLE));
        price = cursor.getInt(cursor.getColumnIndex(BookContract.COL_PRICE));
        cover = cursor.getString(cursor.getColumnIndex(BookContract.COL_COVER));
    }
    @Override
    public int hashCode() {
        int result = getIsbn() != null ? getIsbn().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + getPrice();
        result = 31 * result + (getCover() != null ? getCover().hashCode() : 0);
        return result;
    }
}
