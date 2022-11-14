module com.example.browser {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.web;
    requires lombok;


    opens com.example.browser to javafx.fxml;
    exports com.example.browser;
    exports com.example.browser.Singleton;
    opens com.example.browser.Singleton to javafx.fxml;
    exports com.example.browser.ui;
    opens com.example.browser.ui to javafx.fxml;
}