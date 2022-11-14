package com.example.browser.TubManager;

import com.example.browser.siteManager.Site;
import com.example.browser.siteManager.SiteManager;
import com.example.browser.ui.UI;
import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import lombok.NonNull;

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
        ui.setHandlerLikeBtn(this::HandlerLikePageBtn);
        ui.SetHandlerButtonGroupClicked(this::HandlerButtonGroup);

        Site st = siteManager.AddNewSite("https://google.com");
        ui.AddButtonToButtonGroup(st.getId());
        ui.ChangeSiteView(st);
    }

    private void HandlerPrevBtn()     { ShowAlert(); }
    private void HandlerNextBtn()     { ShowAlert(); }
    private void HandlerReloadBtn()   { ShowAlert(); }
    private void HandlerAddTabBtn()   {
        Site st = siteManager.AddNewSite("https://"+ui.getTFText());
        ui.AddButtonToButtonGroup(st.getId());
        ui.ChangeSiteView(st);
    }
    private void HandlerFindBtn()     {
        siteManager.ChangeSite("https://"+ui.getTFText());

    }
    private void HandlerHistoryBtn()  { ShowAlert(); }
    private void HandlerSavePageBtn() { ShowAlert(); }
    private void HandlerLikePageBtn() { ShowAlert(); }

    private void HandlerButtonGroup(UUID siteID) {
        siteManager.ChangeTub(siteID);
        ui.ChangeSiteView(siteManager.getCurrentSite());
    }
    private void HandlerSiteStatusChanged(Site site) {
        if (site.getState() == Worker.State.FAILED || site.getState() == Worker.State.CANCELLED) {
            ui.RenameButtonOnButtonGroup(site.getId(),"404");
        }
        else {
            ui.RenameButtonOnButtonGroup(site.getId(), site.GetTitle());
        }
    }
    private void ShowAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("In Work :(");
        alert.setHeaderText(null);
        alert.setContentText("Pleas try later!!!");
        alert.showAndWait();
    }
}
