package com.trantuandung.technictest.model;

import android.text.TextUtils;

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
