package com.example.browser.siteManager;

import com.example.browser.ui.SiteView;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Consumer;

public class SiteManager {
    private UUID currentSiteId;
    private final Map<UUID, Site> sites;
    @Setter
    Consumer<Site> listener;

    public SiteManager(AnchorPane root) {
        sites = new TreeMap<UUID, Site>();
        Site baseSite = new Site("https://google.com", this::SiteStatusChanged);
        sites.put(baseSite.getId(), baseSite);
        currentSiteId = baseSite.getId();
    }

    public Site getCurrentSite() {
        return sites.get(currentSiteId);
    }

    public Site AddNewSite(String url) {
        Site newSite = new Site(url, this::SiteStatusChanged);
        sites.put(newSite.getId(), newSite);
        currentSiteId = newSite.getId();
        return newSite;
    }

    public void ChangeSite(UUID siteId) {
        currentSiteId = siteId;
    }

    public void SiteStatusChanged(UUID siteId) {
        listener.accept(sites.get(siteId));
    }
}