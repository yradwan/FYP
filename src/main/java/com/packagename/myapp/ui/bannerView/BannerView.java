package com.packagename.myapp.ui.bannerView;

import com.packagename.myapp.backend.entity.Banner;
import com.packagename.myapp.backend.entity.Total;
import com.packagename.myapp.backend.service.BannerService;
import com.packagename.myapp.backend.service.GameService;
import com.packagename.myapp.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//will have to go back and get rid of all the reflection calls and
//replace them with just regular method calls in an if statement
//won't appear as nice and it might not be as optimized
//but it will remove the exception throwing and reflection making it easier

@Route(value = "banner", layout = MainLayout.class)
@PageTitle("Banner of Game")
public class BannerView extends VerticalLayout implements HasUrlParameter<String> {

    private BannerService bannerService;
    private GameService gameService;
    private String gameID;

    public BannerView(BannerService bannerService, GameService gameService) throws Exception{
        this.bannerService = bannerService;
        this.gameService = gameService;
        this.gameID = gameID;
        addClassName("banner-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getBannerCount(), getData());
    }
    @Override
    public void setParameter(BeforeEvent event,
                             String parameter) {
        setID(parameter);
    }

    private void setID(String parameter) {
        gameID = parameter;
    }

    private Component getBannerCount() {

        List<Banner> bannerList = bannerService.findAll(gameID);
        Span stats = new Span(bannerList.size() + " Users have submitted data for this game");
        stats.addClassName("banner-count");
        return stats;
    }
    //change the methods in this section from reflective to an if statement
    //
    //
    //next to do
    //
    //
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
            //so for this you go through with an if for each rarity section
            //so your if statements will go all the way up to i=9 with a getRarity/raritytotal
            //you continue to use your temps I'll need to remove the method invokes
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
    private int getRarities()  {
        int size = 2;
        Banner adminBanner = getAdmin();
        int maxRarityCount = 10;
        int temps = 0;
        String tempString;
        //change all the reflections to an if statement even if it doesn't appear as clean
        for (int i = 0; i < maxRarityCount; i++) {
            temps = i + 1;
            if (i == 2) {
                tempString = adminBanner.getRarity3();
                if (tempString == null) {
                    size = temps;
                    break;
                }
            }
            if (i == 3) {
                tempString = adminBanner.getRarity4();
                if (tempString == null) {
                    size = temps;
                    break;
                }
            }
            if (i == 4) {
                tempString = adminBanner.getRarity5();
                if (tempString == null) {
                    size = temps;
                    break;
                }
            }
            if (i == 5) {
                tempString = adminBanner.getRarity6();
                if (tempString == null) {
                    size = temps;
                    break;
                }
            }
            if (i == 6) {
                tempString = adminBanner.getRarity7();
                if (tempString == null) {
                    size = temps;
                    break;
                }
            }
            if (i == 7) {
                tempString = adminBanner.getRarity8();
                if (tempString == null) {
                    size = temps;
                    break;
                }
            }
            if (i == 8) {
                tempString = adminBanner.getRarity9();
                if (tempString == null) {
                    size = temps;
                    break;
                }
                if (i == 9) {
                    tempString = adminBanner.getRarity10();
                    if (tempString == null) {
                        size = temps;
                    }
                }
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
    //remove this exception once you finish grabbing the new one
    private Chart getData() throws Exception{
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        Map<String, Integer> statMaps = getStats();
        statMaps.forEach((rarity, value) ->
                dataSeries.add(new DataSeriesItem(rarity, value)));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}
