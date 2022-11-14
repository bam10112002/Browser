package com.example.browser.ui;

import com.example.browser.siteManager.Site;
import javafx.scene.layout.AnchorPane;
import lombok.NonNull;

public class SiteView implements Resizable{
    private Site current;
    private final AnchorPane root;

    public SiteView(AnchorPane root) {
        this.root = root;
    }
    public void ChangeView(@NonNull Site newSite) {
        newSite.getWebView().relocate(0,100);
        newSite.getWebView().setMinSize(root.getMinWidth(), root.getMinHeight()-100);
        if (current != null)
            root.getChildren().remove(current.getWebView());
        root.getChildren().add(newSite.getWebView());
        current = newSite;
    }

    @Override
    public void Resize(double x, double y) {
        this.current.getWebView().setMinSize(x, y - 90);
    }
}
