package com.packagename.myapp.ui.bannerView;

import com.packagename.myapp.backend.entity.Banner;
import com.packagename.myapp.backend.entity.Total;
import com.packagename.myapp.backend.service.BannerService;
import com.packagename.myapp.backend.service.GameService;
import com.packagename.myapp.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "banner", layout = MainLayout.class)
@PageTitle("Banner of Game")
public class BannerView extends VerticalLayout {

    private BannerService bannerService;
    private GameService gameService;
    private String gameID;

    public BannerView(BannerService bannerService, GameService gameService, String gameID){
        this.bannerService = bannerService;
        this.gameService = gameService;
        this.gameID = gameID;
        addClassName("banner-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }
    private Component getBannerCount() {

        List<Banner> bannerList = bannerService.findAll(gameID);
        Span stats = new Span(bannerList.size() + " Users have submitted data for this game");
        stats.addClassName("banner-count");
        return stats;
    }
    private Map<String, Integer> getStats() throws Exception {
        Banner banner = bannerService.find(gameID);
        HashMap<String, Integer> stats = new HashMap<>();
        Total total = new Total();
        Method method;
        String temp1;
        int temp2 = 0;
        List<Total> totals = new ArrayList<>();
        Banner adminBanner = getAdmin();
        int raritySize = getRarities();
        for (int i = 0; i < raritySize; i++) {
            method = Banner.class.getDeclaredMethod("getRarity" + (i+1));
            temp1 = (String) method.invoke(adminBanner);
            method = BannerService.class.getDeclaredMethod("getRarity" + (i+1) + "total");
            temp2 = (int) method.invoke(bannerService, gameID);
            total.setRarity(temp1);
            total.setSize(temp2);
            totals.add(total);
        }
        //to do for tomorrow, make sure to go back and confirm that each and every single rarity
        //gets it own query back in the banner repository
        totals.forEach(total1 -> stats.put(total1.getRarity(), total1.getSize()));
        return stats;
    }
    private int getRarities() throws Exception{
        int size = 2;
        Banner adminBanner = getAdmin();
        int maxRarityCount = 10;
        Method method;
        for (int i = 0; i < maxRarityCount; i++) {
            method = Banner.class.getDeclaredMethod("getRarity" + i+1);
            if (method.invoke(adminBanner) == null){
                size = i+1;
                break;
            }
        }
        return size;
    }

    private Banner getAdmin() {
        List<Banner> bannerList = bannerService.findAll(gameID);
        String admin = "admin";
        Banner adminBanner = new Banner();
        for (int i = 0; i < bannerList.size(); i++) {
            if (bannerList.get(i).getUserName() == admin)
                adminBanner = bannerList.get(i);
        }
        return adminBanner;
    }
}
