package com.example.browser.siteManager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import lombok.Getter;
import lombok.NonNull;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            File file = new File("src/main/resources/pageNotFound.html");
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
        catch (Exception ex) { Failed(); statusChanges.accept(id); }

        worker = webEngine.getLoadWorker();
        worker.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                Logger.getAnonymousLogger().log(Level.INFO, newValue.name());
                if (newValue == Worker.State.FAILED || newValue == Worker.State.CANCELLED) {
                    Failed();
                }
                statusChanges.accept(id);
            }
        });
    }
    public void Reload() {
        webEngine.reload();
    }
    public String GetHtml() {
        return (String) webEngine.executeScript("document.documentElement.outerHTML");
    }

    public Worker.State getState() {
        return  worker.getState();
    }
    public String GetTitle() { return webEngine.getTitle(); }

    public void changeSite(String newUrl) {
        prevUrlList.add(url);
        LoadSite(newUrl);
    }
    public void getHistory(@NonNull LinkedList<WebHistory.Entry> history) {
        history.addAll(webEngine.getHistory().getEntries());
    }

    public void Prev() {
        webEngine.getHistory().go(-1);
        url = webEngine.getHistory().getEntries().get(webEngine.getHistory().getCurrentIndex()).getUrl();
    }
    public void Next() {
        webEngine.getHistory().go(1);
        url = webEngine.getHistory().getEntries().get(webEngine.getHistory().getCurrentIndex()).getUrl();
    }
}