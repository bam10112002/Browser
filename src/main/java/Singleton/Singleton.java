package Singleton;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Getter;

import java.io.File;
import java.net.URL;
public class Singleton {
    public static Page404 page404;
    public static Theme theme;

    static {
        page404 = new Page404();
        theme = Theme.DARK;
    }
}

