package com.example.browser.ui;

import com.example.browser.Singleton.Singleton;
import javafx.scene.control.Button;

import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ButtonGroup {
    TreeMap<UUID, Button> buttons = new TreeMap<>();
    Consumer<UUID> collback;
    void SetTextToButton(UUID id, String text) {
        buttons.get(id).setText(text);
    }
    ButtonGroup(Consumer<UUID> _callback) {
        collback = _callback;
    }
    Button AddButton(UUID siteId) {
        Button btn = new Button();
        btn.setStyle(Singleton.theme.getTubButtonStyle());
        btn.setMinSize(150,30);
        btn.setText("Loading");
        btn.setTranslateX(buttons.size() * 150);
        btn.setOnAction(event -> collback.accept(siteId));
        buttons.put(siteId, btn);
        return btn;
    }
    public void ChangeTheme() {
        for (UUID siteId : buttons.keySet()) {
            buttons.get(siteId).setStyle(Singleton.theme.getTubButtonStyle());
        }
    }
}
