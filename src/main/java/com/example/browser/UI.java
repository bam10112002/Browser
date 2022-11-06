package com.example.browser;

import Singleton.Singleton;
import Singleton.Theme;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class UI {
    private final AnchorPane root;
    private final Button prev;
    private final Button next;
    private final Button reload;
    private final Button addTab;
    private final Button find;
    private final Button love;
    private final Button history;
    private final Button savePage;
    private final Button changeTheme;
    private final TextField searchBar;

    UI() {
        root        = new AnchorPane();
        prev        = new Button();
        next        = new Button();
        reload      = new Button();
        addTab      = new Button();
        find        = new Button();
        love        = new Button();
        history     = new Button();
        savePage    = new Button();
        changeTheme = new Button();
        searchBar   = new TextField();
        root.getChildren().addAll(prev, next, reload, addTab, find, love,
                                   history, savePage, changeTheme, searchBar);
        prev.setTranslateY(50);
        next.setTranslateY(50);
        reload.setTranslateY(50);
        addTab.setTranslateY(50);
        find.setTranslateY(50);
        love.setTranslateY(50);
        history.setTranslateY(50);
        savePage.setTranslateY(50);
        changeTheme.setTranslateY(50);
        searchBar.setTranslateY(50);

        next.setMinSize(30,30);
        next.setMaxSize(30,30);

        prev.setTranslateX(40);
        prev.setMinSize(30,30);
        prev.setMaxSize(30,30);


        reload.setTranslateX(80);
        reload.setMinSize(30,30);
        reload.setMaxSize(30,30);


        searchBar.setTranslateX(120);
        searchBar.setMinSize(500,30);
        searchBar.setMaxSize(500,30);

        addTab.setTranslateX(510 + 3 * 40);
        addTab.setMinSize(30,30);
        addTab.setMaxSize(30,30);

        find.setTranslateX(510 + 4 * 40);
        find.setMinSize(30,30);
        find.setMaxSize(30,30);

        love.setTranslateX(510 + 5 * 40);
        love.setMinSize(30,30);
        love.setMaxSize(30,30);

        history.setTranslateX(510 + 6 * 40);
        history.setMinSize(30,30);
        history.setMaxSize(30,30);

        savePage.setTranslateX(510 + 7 * 40);
        savePage.setMinSize(30,30);
        savePage.setMaxSize(30,30);

        changeTheme.setTranslateX(510 + 8 * 40);
        changeTheme.setMinSize(30,30);
        changeTheme.setMaxSize(30,30);

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

        love.setGraphic(Singleton.theme.getLikeImage());
        love.setStyle(Singleton.theme.getButtonStyle());

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
    }
}
