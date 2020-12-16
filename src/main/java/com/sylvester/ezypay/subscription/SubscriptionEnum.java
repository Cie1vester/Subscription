package com.sylvester.ezypay.subscription;

public enum SubscriptionEnum {

    WEEKLY("W"), MONTHLY("M");

    private String value;

    SubscriptionEnum(String value) {
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
