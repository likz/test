package com.trantuandung.technictest.database.model;

import android.content.ContentValues;

import com.trantuandung.technictest.database.contract.OfferContract;
import com.trantuandung.technictest.database.enums.OfferType;

public class Offer {
    private OfferType type;
    private int sliceValue;
    private int value;

    public Offer(OfferType type, int value) {
        this.value = value;
        this.type = type;
        this.sliceValue = 0;
    }

    public Offer(String type, int value) {
        this(OfferType.matchValue(type), value);
    }

    public Offer(OfferType type, int sliceValue, int value) {
        this.type = type;
        this.sliceValue = sliceValue;
        this.value = value;
    }

    public int getSliceValue() {
        return sliceValue;
    }

    public OfferType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(OfferContract.COL_TYPE, type.getValue());
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
                type.equals(offer.getType()) &&
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
