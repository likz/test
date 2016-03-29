package com.trantuandung.technictest.database.model;

import android.content.ContentValues;
import android.text.TextUtils;

import com.trantuandung.technictest.database.contract.OfferContract;

public class Offer {
    private String type;
    private String sliceValue;
    private int value;

    public String getSliceValue() {
        return sliceValue;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(OfferContract.COL_TYPE, type);
        values.put(OfferContract.COL_SLICE_VALUE, sliceValue);
        values.put(OfferContract.COL_VALUE, value);
        values.put(OfferContract.COL_BOOK_ISBN, "");
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        return value == offer.getValue() &&
                !TextUtils.isEmpty(type) && type.equals(offer.getType()) &&
                (sliceValue != null ? sliceValue.equals(offer.getSliceValue()) : offer.getSliceValue() == null);

    }

    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + (getSliceValue() != null ? getSliceValue().hashCode() : 0);
        result = 31 * result + getValue();
        return result;
    }
}
