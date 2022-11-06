module com.example.browser {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.web;
    requires lombok;


    opens com.example.browser to javafx.fxml;
    exports com.example.browser;
    exports Singleton;
    opens Singleton to javafx.fxml;
}