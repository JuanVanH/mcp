package com.juan.pl;

public enum TitleType {
    ACTIONBAR("actionbar"),
    TITLE("title"),
    SUBTITLE("subtitle"),
    TIMES("times"),
    CLEAR("clear"),
    RESET("reset");

    private String action;

    // getter method
    public String getAction() {
        return this.action;
    }

    // enum constructor - cannot be public or protected
    private TitleType(String action) {
        this.action = action;
    }

}
