package com.example.browser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class HelloApplication extends Application {
//    private static Logger log = Logger.getLogger(HelloApplication.class.getName());
    @Override
    public void start(Stage stage) {
        UI ui = new UI();
        Scene scene = new Scene(ui.getRoot());
        stage.getIcons().add(new Image(new File("src/main/resources/favicon.png").toURI().toString()));
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