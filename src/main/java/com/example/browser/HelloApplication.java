package com.example.browser;

import com.example.browser.siteManager.SiteManager;
import com.example.browser.TubManager.Broker;
import com.example.browser.ui.UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class HelloApplication extends Application {
//    private static Logger log = Logger.getLogger(HelloApplication.class.getName());
    @Override
    public void start( Stage stage) {
        stage.getIcons().add(new Image(new File("src/main/resources/favicon.png").toURI().toString()));
        stage.setMinHeight(700);
        stage.setMinWidth(1000);


        UI ui = new UI();
        Broker broker = new Broker(ui);
        ui.Resize(stage.getMinWidth(), stage.getMinHeight());


        //Resize event
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            ui.Resize(stage.getWidth(), stage.getHeight());
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            ui.Resize(stage.getWidth(), stage.getHeight());
        });

        Scene scene = new Scene(ui.getRoot());
        stage.setTitle("Browser");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static Object print() {
        System.out.println("Tuk Tuk");
        return null;
    }
}