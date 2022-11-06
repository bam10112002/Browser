package Singleton;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Getter;

import java.io.File;
import java.net.URL;

public class Page404 {
    WebEngine webEngine;
    @Getter
    WebView webView;

    Page404() {
        webView = new WebView();
        webEngine = webView.getEngine();
        try {
            File file = new File("src/main/resources/404.html");
            URL url = file.toURI().toURL();
            webEngine.load(url.toString());
            System.out.println(webEngine.getTitle());
            webView.setMinSize(500, 500);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
