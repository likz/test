package com.trantuandung.technictest.database.enums;

import android.text.TextUtils;

import com.trantuandung.technictest.database.model.Offer;

public enum OfferType {
    UNKNOWN(-1),
    PERCENTAGE(0),
    MINUS(1),
    SLICE(2);

    private final int value;

    OfferType(int value) {
        this.value = value;
    }

    public static OfferType matchValue(int value) {
        switch (value) {
            case 0:
                return PERCENTAGE;
            case 1:
                return MINUS;
            case 2:
                return SLICE;
            default:
                return UNKNOWN;
        }
    }

    public static OfferType getType(String value) {
        if(TextUtils.isEmpty(value)){
            return UNKNOWN;
        }

        switch (value) {
            case "percentage":
                return PERCENTAGE;
            case "minus":
                return MINUS;
            case "slice":
                return SLICE;
            default:
                return UNKNOWN;
        }
    }

    public static OfferType getOfferType(Offer offer) {
        return getType(offer.getType());
    }

    public int getValue() {
        return this.value;
    }

}
