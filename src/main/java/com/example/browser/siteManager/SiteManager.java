package com.example.browser.siteManager;

import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import lombok.Setter;

import java.util.*;
import java.util.function.Consumer;

public class SiteManager {
    private UUID currentSiteId;
    HashSet<String> favoriteList;
    private final Map<UUID, Site> sites;
    @Setter
    Consumer<Site> listener;

    public SiteManager() {
        favoriteList = new HashSet<>();
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

    public void ReloadCurrentSite() {
        Site st = sites.get(currentSiteId);
        st.Reload();
    }
    public String getCurrentSiteHtml() {
        return sites.get(currentSiteId).GetHtml();
    }

    public LinkedList<WebHistory.Entry> GetHistory() {
        LinkedList<WebHistory.Entry> history = new LinkedList<>();
        for (UUID id : sites.keySet() ) {
            sites.get(id).getHistory(history);
        }
        return history;
    }

    public void Prev() {
        sites.get(currentSiteId).Prev();
    }
    public void Next() {
        sites.get(currentSiteId).Next();
    }
    public void AddCurrentToFavoriteList() {
        favoriteList.add(sites.get(currentSiteId).getUrl());
    }
    public HashSet<String> GetFavoriteList() {
        return favoriteList;
    }
}