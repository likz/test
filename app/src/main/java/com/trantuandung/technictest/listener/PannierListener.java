package com.trantuandung.technictest.listener;

import com.trantuandung.technictest.model.Book;

import java.util.List;

public interface PannierListener {

    void deleteBook(Book book);

    int totalCart();

    List<Book> getBookList();
}
