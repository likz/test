package com.trantuandung.technictest.listener;

public interface CommercialOfferCallBack {
    void success(int amount);

    void failure(String messageError);
}
