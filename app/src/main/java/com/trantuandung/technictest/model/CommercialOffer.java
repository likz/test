package com.trantuandung.technictest.model;

import com.trantuandung.technictest.listener.CartListener;
import com.trantuandung.technictest.listener.CommercialOfferCallBack;
import com.trantuandung.technictest.server.ItemsRequester;

import java.util.List;

public class CommercialOffer {
    CartListener cartListener;

    public CommercialOffer(CartListener cartListener){
        this.cartListener = cartListener;
    }

    public void getOffert(final CommercialOfferCallBack callback) {
        final List<Book> books = cartListener.getBookList();
        if (books.isEmpty()) {
            callback.success(0);
        }else{
            ItemsRequester itemsRequester = new ItemsRequester();
            try {
                List<Offer> offerList = itemsRequester.getAllOfferByBooks(isbnValues(cartListener.getBookList()));
                int bestOffer = 0;
                for (Offer offer : offerList) {
                    int currentOffer = offer.calculOffer(books);
                    if (currentOffer > bestOffer) {
                        bestOffer = currentOffer;
                    }
                }
                callback.success(cartListener.totalCart() - bestOffer);
            } catch (Exception e) {
                callback.failure(e.getMessage());
            }

        }
    }

    private String isbnValues(List<Book> bookList) {
        StringBuilder isbnValues = new StringBuilder();
        for (Book book : bookList) {
            if (isbnValues.length() != 0) {
                isbnValues.append(",");
            }
            isbnValues.append(book.getIsbn());
        }
        return isbnValues.toString();
    }
}
