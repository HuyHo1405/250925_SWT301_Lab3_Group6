package com.DateTimeChecker.demo.model;

import lombok.Data;

@Data
public class DateTimeModel {
    private String day;
    private String month;
    private String year;
    private String message;

    public DateTimeModel(String year, String month, String day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
