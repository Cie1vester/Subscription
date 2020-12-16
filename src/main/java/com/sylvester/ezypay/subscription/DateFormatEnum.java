package com.sylvester.ezypay.subscription;

public enum DateFormatEnum {

    Day_Month_Year("dd/MM/yyyy");
    
    private String dateFormat;

    DateFormatEnum(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
