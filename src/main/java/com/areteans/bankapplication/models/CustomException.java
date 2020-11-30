package com.areteans.bankapplication.models;

public class CustomException extends Exception {
    String s;
    public CustomException(String s) {
        this.s= s;
    }

    @Override
    public String toString() {
        return " " + s;
    }
}
