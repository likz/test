package com.trantuandung.technictest.database.model;

import android.content.ContentValues;
import android.text.TextUtils;

import com.trantuandung.technictest.database.contract.BookContract;

public class Book {
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

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(BookContract.COL_ISBN, isbn);
        values.put(BookContract.COL_TITLE, title);
        values.put(BookContract.COL_PRICE, price);
        values.put(BookContract.COL_COVER, cover);
        return values;
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

    @Override
    public int hashCode() {
        int result = getIsbn() != null ? getIsbn().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + getPrice();
        result = 31 * result + (getCover() != null ? getCover().hashCode() : 0);
        return result;
    }
}
