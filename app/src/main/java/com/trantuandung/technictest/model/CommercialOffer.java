package com.trantuandung.technictest.model;

import com.trantuandung.technictest.listener.PannierListener;
import com.trantuandung.technictest.listener.CommercialOfferCallBack;
import com.trantuandung.technictest.server.ItemsRequester;

import java.util.List;

public class CommercialOffer {
    PannierListener pannierListener;

    public CommercialOffer(PannierListener pannierListener){
        this.pannierListener = pannierListener;
    }

    public void getOffert(final CommercialOfferCallBack callback) {
        final List<Book> books = pannierListener.getBookList();
        if (books.isEmpty()) {
            callback.success(0);
        }else{
            ItemsRequester itemsRequester = new ItemsRequester();
            try {
                List<Offer> offerList = itemsRequester.getAllOfferByBooks(isbnValues(pannierListener.getBookList()));
                int bestOffer = 0;
                for (Offer offer : offerList) {
                    int currentOffer = offer.calculOffer(books);
                    if (currentOffer > bestOffer) {
                        bestOffer = currentOffer;
                    }
                }
                callback.success(pannierListener.totalCart() - bestOffer);
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
