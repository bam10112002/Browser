package com.example.browser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
//    private static Logger log = Logger.getLogger(HelloApplication.class.getName());
    @Override
    public void start(Stage stage) {
        UI ui = new UI();
        Scene scene = new Scene(ui.getRoot());
        stage.setMinHeight(700);
        stage.setMinWidth(1000);
        stage.setTitle("Browser");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}