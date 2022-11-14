package com.example.browser.siteManager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Getter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

public class Site {
    @Getter
    private UUID id;
    @Getter
    private String url;
    ArrayList<String> prevUrlList;
    @Getter
    public WebView webView;
    private final WebEngine webEngine;
    private Worker<Void> worker;

    private final Consumer<UUID> statusChanges;

    public Site(String url, Consumer<UUID> statusChanges) {
        prevUrlList = new ArrayList<>();
        this.id = UUID.randomUUID();
        this.statusChanges = statusChanges;
        webView = new WebView();
        webEngine = webView.getEngine();
        LoadSite(url);
    }
    private void Failed() {
        try {
            File file = new File("src/main/resources/404.html");
            URL url = file.toURI().toURL();
            webEngine.load(url.toString());
        }
        catch (MalformedURLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    private void LoadSite(String url) {
        this.url = url;
        try { URL _url = new URL(url); webEngine.load(url);}
        catch (Exception ex) { Failed(); }

        worker = webEngine.getLoadWorker();
        worker.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.FAILED) {
                    Failed();
                }
                statusChanges.accept(id);
            }
        });
    }

    public Worker.State getState() {
        return  worker.getState();
    }
    public String GetTitle() { return webEngine.getTitle(); }

    public void changeSite(String newUrl) {
        prevUrlList.add(url);
        LoadSite(newUrl);
    }
}