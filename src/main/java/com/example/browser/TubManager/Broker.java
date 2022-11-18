package com.example.browser.TubManager;

import com.example.browser.siteManager.Site;
import com.example.browser.siteManager.SiteManager;
import com.example.browser.ui.UI;
import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import javafx.scene.web.WebHistory;
import lombok.NonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

public class Broker {
    SiteManager siteManager;
    UI ui;

    public Broker(@NonNull UI _ui) {
        ui = _ui;
        siteManager = new SiteManager();
        siteManager.setListener(this::HandlerSiteStatusChanged);

        ui.setHandlerPrevBtn(this::HandlerPrevBtn);
        ui.setHandlerNextBtn(this::HandlerNextBtn);
        ui.setHandlerReloadBtn(this::HandlerReloadBtn);
        ui.setHandlerAddTabBtn(this::HandlerAddTabBtn);
        ui.setHandlerFindBtn(this::HandlerFindBtn);
        ui.setHandlerHistoryBtn(this::HandlerHistoryBtn);
        ui.setHandlerSavePageBtn(this::HandlerSavePageBtn);
        ui.setHandlerFavoriteBtn(this::HandlerFavoriteList);
        ui.setHandlerShowFavoriteList(this::HandlerShowFavoriteList);
        ui.SetHandlerButtonGroupClicked(this::HandlerButtonGroup);

        Site st = siteManager.AddNewSite("https://google.com");
        ui.AddButtonToButtonGroup(st.getId());
        ui.ChangeSiteView(st);
    }

    private void HandlerPrevBtn()     {
        siteManager.Prev();
        ui.setTFText(siteManager.getCurrentSite().getUrl());
    }
    private void HandlerNextBtn()     {
        siteManager.Next();
        ui.setTFText(siteManager.getCurrentSite().getUrl());
    }
    private void HandlerReloadBtn()   { siteManager.ReloadCurrentSite(); }
    private void HandlerAddTabBtn()   {
        Site st = siteManager.AddNewSite("https://"+ui.getTFText());
        ui.AddButtonToButtonGroup(st.getId());
        ui.ChangeSiteView(st);
    }
    private void HandlerFindBtn()     {
        if (ui.getTFText().contains("https://") || ui.getTFText().contains("http://"))
            siteManager.ChangeSite(ui.getTFText());
        else
            siteManager.ChangeSite("https://" + ui.getTFText());
    }
    private void HandlerHistoryBtn() {
//        ShowHistory(siteManager.GetHistory());
        for (WebHistory.Entry entery : siteManager.GetHistory()) {
            System.out.println(entery);
        }
    }
    private void HandlerSavePageBtn() {
        try {
            FileWriter fw = new FileWriter("src/main/resources/pages/page1.html");
            fw.write(siteManager.getCurrentSiteHtml());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void HandlerFavoriteList() {
        siteManager.AddCurrentToFavoriteList();
    }
    private void HandlerShowFavoriteList() {
        for (String url : siteManager.GetFavoriteList()) {
            System.out.println(url);
        }
    }
    private void HandlerButtonGroup(UUID siteID) {
        siteManager.ChangeTub(siteID);
        ui.ChangeSiteView(siteManager.getCurrentSite());
    }
    private void HandlerSiteStatusChanged(@NonNull Site site) {
        if (site.getState() == Worker.State.FAILED || site.getState() == Worker.State.CANCELLED) {
            ui.RenameButtonOnButtonGroup(site.getId(),"404");
        }
        else {
            ui.RenameButtonOnButtonGroup(site.getId(), site.GetTitle());
        }
    }
    private void ShowAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setWidth(1000);
        alert.setTitle("History");
        alert.setHeaderText(null);
        alert.setContentText("Pleas try later!!!");
        alert.showAndWait();
    }
    private void ShowHistory(@NonNull LinkedList<WebHistory.Entry> history) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("In Work :(");
        alert.setHeaderText(null);

        StringBuilder content = new StringBuilder();
        for (WebHistory.Entry entry : history) {
            content.append(entry.getUrl()).append("\t").append(entry.getLastVisitedDate());
        }

        alert.setContentText(content.toString());
        alert.showAndWait();
    }
}
