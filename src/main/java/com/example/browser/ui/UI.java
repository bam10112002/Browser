package com.example.browser.ui;

import com.example.browser.Singleton.Singleton;
import com.example.browser.Singleton.Theme;
import com.example.browser.siteManager.Site;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.UUID;
import java.util.function.Consumer;


public class UI implements Resizable{
    private final AnchorPane root;
    private final Button prev;
    private final Button next;
    private final Button reload;
    private final Button addTab;
    private final Button find;
    private final Button like;
    private final Button history;
    private final Button savePage;
    private final Button changeTheme;
    private final Button showFavoriteList;
    private final TextField searchBar;

    SiteView siteView;
    ButtonGroup buttonGroup;
    Consumer<UUID> buttonGroupCallback;
    int searchBarWidth = 510;

    public UI() {
        root        = new AnchorPane();
        prev        = new Button();
        next        = new Button();
        reload      = new Button();
        addTab      = new Button();
        find        = new Button();
        like = new Button();
        history     = new Button();
        savePage    = new Button();
        changeTheme = new Button();
        showFavoriteList = new Button();
        searchBar   = new TextField();
        siteView = new SiteView(root);
        buttonGroup = new ButtonGroup(this::ClickedButtonOnButtonGroup);
        root.getChildren().addAll(prev, next, reload, addTab, find, like,
                                   history, savePage, changeTheme, showFavoriteList, searchBar);

        int yShift = 35;
        prev.setTranslateY(yShift);
        next.setTranslateY(yShift);
        reload.setTranslateY(yShift);
        addTab.setTranslateY(yShift);
        find.setTranslateY(yShift);
        like.setTranslateY(yShift);
        history.setTranslateY(yShift);
        savePage.setTranslateY(yShift);
        changeTheme.setTranslateY(yShift);
        showFavoriteList.setTranslateY(yShift);
        searchBar.setTranslateY(yShift);

        prev.setTranslateX(0);
        prev.setMinSize(30,30);
        prev.setMaxSize(30,30);

        next.setTranslateX(40);
        next.setMinSize(30,30);
        next.setMaxSize(30,30);

        reload.setTranslateX(80);
        reload.setMinSize(30,30);
        reload.setMaxSize(30,30);


        searchBar.setTranslateX(120);
        searchBar.setMinSize(searchBarWidth - 10,30);
        searchBar.setMaxSize(searchBarWidth - 10,30);

        addTab.setTranslateX(searchBarWidth + 3 * 40);
        addTab.setMinSize(30,30);
        addTab.setMaxSize(30,30);

        find.setTranslateX(searchBarWidth + 4 * 40);
        find.setMinSize(30,30);
        find.setMaxSize(30,30);

        like.setTranslateX(searchBarWidth + 5 * 40);
        like.setMinSize(30,30);
        like.setMaxSize(30,30);

        history.setTranslateX(searchBarWidth + 6 * 40);
        history.setMinSize(30,30);
        history.setMaxSize(30,30);

        savePage.setTranslateX(searchBarWidth + 7 * 40);
        savePage.setMinSize(30,30);
        savePage.setMaxSize(30,30);

        changeTheme.setTranslateX(searchBarWidth + 8 * 40);
        changeTheme.setMinSize(30,30);
        changeTheme.setMaxSize(30,30);

        showFavoriteList.setTranslateX(searchBarWidth + 9 * 40);
        showFavoriteList.setMinSize(30,30);
        showFavoriteList.setMaxSize(30,30);

        changeTheme.setOnAction(event -> {
            if (Singleton.theme == Theme.DARK) {
                Singleton.theme = Theme.LIGHT;
            }
            else  if (Singleton.theme == Theme.LIGHT) {
                Singleton.theme = Theme.DARK;
            }
            SetStyles();
        });
        SetStyles();
    }
    public Parent getRoot() {
        return root;
    }
    private void SetStyles() {
        root.setStyle(Singleton.theme.getAnchorPaneStyle());

        changeTheme.setGraphic(Singleton.theme.getTheme());
        changeTheme.setStyle(Singleton.theme.getButtonStyle());

        savePage.setGraphic(Singleton.theme.getSaveImage());
        savePage.setStyle(Singleton.theme.getButtonStyle());

        history.setGraphic(Singleton.theme.getHistoryImage());
        history.setStyle(Singleton.theme.getButtonStyle());

        like.setGraphic(Singleton.theme.getLikeImage());
        like.setStyle(Singleton.theme.getButtonStyle());

        find.setGraphic(Singleton.theme.getFindImage());
        find.setStyle(Singleton.theme.getButtonStyle());

        addTab.setGraphic(Singleton.theme.getAddTabImage());
        addTab.setStyle(Singleton.theme.getButtonStyle());

        reload.setGraphic(Singleton.theme.getReloadImage());
        reload.setStyle(Singleton.theme.getButtonStyle());

        prev.setGraphic(Singleton.theme.getPrevImage());
        prev.setStyle(Singleton.theme.getButtonStyle());

        next.setGraphic(Singleton.theme.getNextImage());
        next.setStyle(Singleton.theme.getButtonStyle());

        buttonGroup.ChangeTheme();
    }

    public void setHandlerPrevBtn    (ButtonInterface handler) {
        prev.setOnAction(event -> handler.execute());
    }
    public void setHandlerShowFavoriteList    (ButtonInterface handler) {
        showFavoriteList.setOnAction(event -> handler.execute());
    }
    public void setHandlerNextBtn    (ButtonInterface handler) {
        next.setOnAction(event -> handler.execute());
    }
    public void setHandlerReloadBtn  (ButtonInterface handler) {
        reload.setOnAction(event -> handler.execute());
    }
    public void setHandlerAddTabBtn  (ButtonInterface handler) {
        addTab.setOnAction(event -> handler.execute());
    }
    public void setHandlerFindBtn    (ButtonInterface handler) {
        find.setOnAction(event -> handler.execute());
    }
    public void setHandlerHistoryBtn (ButtonInterface handler) {
        history.setOnAction(event -> handler.execute());
    }
    public void setHandlerSavePageBtn(ButtonInterface handler) {
        savePage.setOnAction(event -> handler.execute());
    }
    public void setHandlerFavoriteBtn(ButtonInterface handler) {
        like.setOnAction(event -> handler.execute());
    }

    public String getTFText() {
        return searchBar.getText();

    }
    public void setTFText(String url) {
        searchBar.setText(url);
    }
    public void ClickedButtonOnButtonGroup(UUID siteId) {
        buttonGroupCallback.accept(siteId);
    }
    public void SetHandlerButtonGroupClicked(Consumer<UUID> callback) {
        buttonGroupCallback = callback;
    }
    public void AddButtonToButtonGroup(UUID siteId) {
        root.getChildren().add(buttonGroup.AddButton(siteId));
    }
    public void RenameButtonOnButtonGroup(UUID id, String name) {
        buttonGroup.SetTextToButton(id, name);
    }
    public void ChangeSiteView(Site site) {
        siteView.ChangeView(site);
        siteView.Resize(root.getWidth(), root.getHeight());
    }
    @Override
    public void Resize(double x, double y) {
        searchBarWidth = (int)(x - 10*40) - 10;
        addTab.setTranslateX     (searchBarWidth + 3 * 40);
        find.setTranslateX       (searchBarWidth + 4 * 40);
        like.setTranslateX       (searchBarWidth + 5 * 40);
        history.setTranslateX    (searchBarWidth + 6 * 40);
        savePage.setTranslateX   (searchBarWidth + 7 * 40);
        changeTheme.setTranslateX(searchBarWidth + 8 * 40);
        showFavoriteList.setTranslateX(searchBarWidth + 9 * 40);

        searchBar.setMinSize(searchBarWidth - 10,30);
        searchBar.setMaxSize(searchBarWidth - 10,30);

        siteView.Resize(x,y);
    }
}
