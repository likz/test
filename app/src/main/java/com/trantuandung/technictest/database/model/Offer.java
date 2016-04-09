package com.trantuandung.technictest.database.model;

import android.content.ContentValues;
import android.text.TextUtils;

import com.trantuandung.technictest.database.contract.OfferContract;

public class Offer {
    private String type;
    private int sliceValue;
    private int value;

    public int getSliceValue() {
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
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        return value == offer.getValue() &&
                !TextUtils.isEmpty(type) && type.equals(offer.getType()) &&
                sliceValue == offer.getSliceValue();

    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + getSliceValue();
        result = 31 * result + getValue();
        return result;
    }
}
