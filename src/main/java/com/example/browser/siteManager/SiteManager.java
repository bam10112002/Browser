package com.example.browser.siteManager;

import javafx.concurrent.Worker;
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

    public SiteManager() {
        sites = new TreeMap<UUID, Site>();
        currentSiteId = null;
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

    public void ChangeTub(UUID siteId) {
        currentSiteId = siteId;
    }
    public void ChangeSite(String newUrl) {
        sites.get(currentSiteId).changeSite(newUrl);
    }

    public void SiteStatusChanged(UUID siteId) {
        Site site = sites.get(siteId);
        listener.accept(sites.get(siteId));
    }
}