package com.example.browser;

import Singleton.Singleton;
import Singleton.Theme;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.web.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class HelloApplication extends Application {
    private static Logger log = Logger.getLogger(HelloApplication.class.getName());
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
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