package com.example.browser.TubManager;

import com.example.browser.siteManager.SiteManager;
import com.example.browser.ui.UI;
import javafx.scene.control.Alert;
import lombok.NonNull;

import java.util.UUID;

public class Broker {
    SiteManager siteManager;
    UI ui;

    public Broker(@NonNull UI _ui) {
        ui = _ui;
        siteManager = null;
        ui.setHandlerPrevBtn(this::HandlerPrevBtn);
        ui.setHandlerNextBtn(this::HandlerNextBtn);
        ui.setHandlerReloadBtn(this::HandlerReloadBtn);
        ui.setHandlerAddTabBtn(this::HandlerAddTabBtn);
        ui.setHandlerFindBtn(this::HandlerFindBtn);
        ui.setHandlerHistoryBtn(this::HandlerHistoryBtn);
        ui.setHandlerSavePageBtn(this::HandlerSavePageBtn);
        ui.setHandlerLikeBtn(this::HandlerLikePageBtn);
        ui.SetHandlerButtonGroupClicked(this::HandlerButtonGroup);

        ui.AddButtonToButtonGroup(UUID.randomUUID());
    }

    private void HandlerPrevBtn()     { ShowAlert(); }
    private void HandlerNextBtn()     { ShowAlert(); }
    private void HandlerReloadBtn()   { ShowAlert(); }
    private void HandlerAddTabBtn()   { ShowAlert(); }
    private void HandlerFindBtn()     { ShowAlert(); }
    private void HandlerHistoryBtn()  { ShowAlert(); }
    private void HandlerSavePageBtn() { ShowAlert(); }
    private void HandlerLikePageBtn() { ShowAlert(); }

    private void HandlerButtonGroup(UUID siteID) { ShowAlert(); }
    private void ShowAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("In Work :(");
        alert.setHeaderText(null);
        alert.setContentText("Pleas try later!!!");
        alert.showAndWait();
    }
}
