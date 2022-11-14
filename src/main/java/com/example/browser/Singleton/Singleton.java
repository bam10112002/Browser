package com.example.browser.Singleton;

public class Singleton {
    public static Page404 page404;
    public static Theme theme;

    static {
        page404 = new Page404();
        theme = Theme.DARK;
    }
}

