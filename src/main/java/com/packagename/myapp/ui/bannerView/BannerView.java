package com.packagename.myapp.ui.bannerView;

import com.packagename.myapp.backend.entity.Banner;
import com.packagename.myapp.backend.entity.Total;
import com.packagename.myapp.backend.service.BannerService;
import com.packagename.myapp.backend.service.GameService;
import com.packagename.myapp.ui.HomeLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//will have to go back and get rid of all the reflection calls and
//replace them with just regular method calls in an if statement
//won't appear as nice and it might not be as optimized
//but it will remove the exception throwing and reflection making it easier

@Route(value = "banner", layout = HomeLayout.class)
@PageTitle("Banner")
public class BannerView extends VerticalLayout implements HasUrlParameter<String> {

    private BannerService bannerService;
    private GameService gameService;
    private String ID = "";
    private Button button = new Button("Submit");
    private IntegerField rarity1 = new IntegerField();
    private IntegerField rarity2 = new IntegerField();
    private IntegerField rarity3 = new IntegerField();
    private IntegerField rarity4 = new IntegerField();
    private IntegerField rarity5 = new IntegerField();
    private IntegerField rarity6 = new IntegerField();
    private IntegerField rarity7 = new IntegerField();
    private IntegerField rarity8 = new IntegerField();
    private IntegerField rarity9 = new IntegerField();
    private IntegerField rarity10 = new IntegerField();
    //our next few steps are finding out how to pass the parameter properly and
    //adding a form to add our stats to this page
    //once that's done head on to login

    //gameID = "gbf0520";
    //move totals back to chart

    //move back to chart after testing
    //to be removed
    //private Grid<Banner> grid = new Grid<>(Banner.class);

    public BannerView(BannerService bannerService, GameService gameService) {
        this.bannerService = bannerService;
        this.gameService = gameService;
        addClassName("banner-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }
    @Override
    public void setParameter(BeforeEvent event,
                             String parameter) {
        ID = parameter;
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (object instanceof UserDetails) {
            username = ((UserDetails)object).getUsername();
        } else {
            username = object.toString();
        }
        addUI(username);
    }

    private void addUI(String userName) {
        List<Banner> bannerCheck = bannerService.findAll(ID);
        //System.out.println(bannerCheck);
        if (bannerCheck.size() != 0 ){
            VerticalLayout averageLayout = new VerticalLayout();
            averageLayout.add(getData(), getAverageStats());
            VerticalLayout userLayout = new VerticalLayout();
            userLayout.add(getData(userName), getUserRates(userName));
            HorizontalLayout layout = new HorizontalLayout();
            layout.add(averageLayout, userLayout);
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            button.addClickListener(e -> submitData(userName));
            add(getBannerCount(), layout, getUserForm(userName), button);
        }
        else
            add(new Span("Go back to List and Select a game"));
    }

/*
    private void setID(String parameter) {
        gameID = parameter;
    }
*/
   // private void setGameID(String ID){
   //     gameID = ID;
   // }

    private Component getBannerCount() {
        //will need to fix this and get it to show the correct number of users
        //I also need to make sure to fix the stats so it'll properly pass the stats
        //right now I suspect the issue might be with the find all command
        //for the moment that they are currently showing all users in the list
        //rather than just  the correct set of users
        //most likely the issue could be the parameter passing
        List<Banner> bannerList = bannerService.findAll(ID);
        //might need to create a for list that grabs all the banners with gameID
        Span stats = new Span((bannerList.size() - 1) + " Users have submitted data for this game");
        stats.addClassName("banner-count");
        return stats;
    }
    //change the methods in this section from reflective to an if statement
    //
    //
    //next to do
    //
    //

    /*
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
    */

    private  Component getAverageStats(){
        Map<String, Integer> statMaps = getStats();
        VerticalLayout averageStats = new VerticalLayout();
        int total = 0;
        //this for loop is used to get the total stats for this
        for (String i : statMaps.keySet()) {
            total += statMaps.get(i);
        }
        for (String i : statMaps.keySet()) {
            //I'm not quite sure why but these aren't going to two decimal points
            float temp =  statMaps.get(i);
            float average = (temp / total) * 100;
            String tempString = String.format("%.2f", average);
            averageStats.add(new Span("The average rate for a " + i + " is " + tempString + "%"));
        }
        return averageStats;
    }

    private Map<String, Integer> getStats() {
        //Banner banner = bannerService.find(gameID);
        HashMap<String, Integer> stats = new HashMap<>();
        Total total = new Total();
        List<Total> totals = new ArrayList<>();
        String temp1 = "";
        long temp2 = 0;
        Banner adminBanner = getAdmin();
        int raritySize = getRarities();
        for (int i = 0; i < raritySize; i++) {
            total = new Total();
            //so for this you go through with an if for each rarity section
            //so your if statements will go all the way up to i=9 with a getRarity/raritytotal
            //you continue to use your temps I'll need to remove the method invokes
            //decided to go switch instead
            switch (i) {
                case 0 :
                    temp1 = adminBanner.getRarity1();
                    temp2 = bannerService.getRarity1total(ID);
                    break;
                case 1 :
                    temp1 = adminBanner.getRarity2();
                    temp2 = bannerService.getRarity2total(ID);
                    break;
                case 2 :
                    temp1 = adminBanner.getRarity3();
                    temp2 = bannerService.getRarity3total(ID);
                    break;
                case 3 :
                    temp1 = adminBanner.getRarity4();
                    temp2 = bannerService.getRarity4total(ID);
                    break;
                case 4 :
                    temp1 = adminBanner.getRarity5();
                    temp2 = bannerService.getRarity5total(ID);
                    break;
                case 5 :
                    temp1 = adminBanner.getRarity6();
                    temp2 = bannerService.getRarity6total(ID);
                    break;
                case 6 :
                    temp1 = adminBanner.getRarity7();
                    temp2 = bannerService.getRarity7total(ID);
                    break;
                case 7 :
                    temp1 = adminBanner.getRarity8();
                    temp2 = bannerService.getRarity8total(ID);
                    break;
                case 8 :
                    temp1 = adminBanner.getRarity9();
                    temp2 = bannerService.getRarity9total(ID);
                    break;
                case 9 :
                    temp1 = adminBanner.getRarity10();
                    temp2 = bannerService.getRarity10total(ID);
            }
            total.setRarity(temp1);
            total.setSize((int)temp2);
            totals.add(total);
        }
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
        //was originally going to have a switch statement but if should probably be better here
        for (int i = 0; i < maxRarityCount; i++) {
            temps = i;
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
        List<Banner> bannerList = bannerService.findAll(ID);
        String admin = "admin";
        Banner adminBanner = new Banner();
        for (int i = 0; i < bannerList.size(); i++) {
            if ((bannerList.get(i).getUserName()).equals(admin))
                adminBanner = bannerList.get(i);
        }
        return adminBanner;
    }
    //remove this exception once you finish grabbing the new one
    private Chart getData() {
        Chart chart = new Chart(ChartType.PIE);
        DataSeries dataSeries = new DataSeries();
        Map<String, Integer> statMaps = getStats();
        statMaps.forEach((rarity, value) ->
                dataSeries.add(new DataSeriesItem(rarity, value)));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }

    private Chart getData(String userName) {
        Chart chart = new Chart(ChartType.PIE);
        DataSeries dataSeries = new DataSeries();
        Map<String, Integer> statMaps = getStats(userName);
        statMaps.forEach((rarity, value) ->
                dataSeries.add(new DataSeriesItem(rarity, value)));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }

    //need to be fixed
    private  Component getUserRates(String userName){
        Map<String, Integer> statMaps = getStats(userName);
        VerticalLayout averageStats = new VerticalLayout();
        int total = 0;
        //this for loop is used to get the total stats for this
        for (String i : statMaps.keySet()) {
            total += statMaps.get(i);
        }
        for (String i : statMaps.keySet()) {
            //I'm not quite sure why but these aren't going to two decimal points
            float temp =  statMaps.get(i);
            float average = (temp / total) * 100;
            String tempString = String.format("%.2f", average);
            averageStats.add(new Span("Your average rate for a " + i + " is " + tempString + "%"));
        }
        return averageStats;
    }

    private Map<String, Integer> getStats(String userName) {
        Banner banner = bannerService.find(ID, userName);
        HashMap<String, Integer> stats = new HashMap<>();
        Total total = new Total();
        List<Total> totals = new ArrayList<>();
        String temp1 = "";
        long temp2 = 0;
        Banner adminBanner = getAdmin();
        int raritySize = getRarities();
        for (int i = 0; i < raritySize; i++) {
            total = new Total();
            //so for this you go through with an if for each rarity section
            //so your if statements will go all the way up to i=9 with a getRarity/raritytotal
            //you continue to use your temps I'll need to remove the method invokes
            //decided to go switch instead
            switch (i) {
                case 0 :
                    temp1 = adminBanner.getRarity1();
                    temp2 = banner.getRarity1Value();
                    break;
                case 1 :
                    temp1 = adminBanner.getRarity2();
                    temp2 = banner.getRarity2Value();
                    break;
                case 2 :
                    temp1 = adminBanner.getRarity3();
                    temp2 = banner.getRarity3Value();
                    break;
                case 3 :
                    temp1 = adminBanner.getRarity4();
                    temp2 = banner.getRarity4Value();
                    break;
                case 4 :
                    temp1 = adminBanner.getRarity5();
                    temp2 = banner.getRarity5Value();
                    break;
                case 5 :
                    temp1 = adminBanner.getRarity6();
                    temp2 = banner.getRarity6Value();
                    break;
                case 6 :
                    temp1 = adminBanner.getRarity7();
                    temp2 = banner.getRarity7Value();
                    break;
                case 7 :
                    temp1 = adminBanner.getRarity8();
                    temp2 = banner.getRarity8Value();
                    break;
                case 8 :
                    temp1 = adminBanner.getRarity9();
                    temp2 = banner.getRarity9Value();
                    break;
                case 9 :
                    temp1 = adminBanner.getRarity10();
                    temp2 = banner.getRarity10Value();
            }
            total.setRarity(temp1);
            total.setSize((int)temp2);
            totals.add(total);
        }
        totals.forEach(total1 -> stats.put(total1.getRarity(), total1.getSize()));
        return stats;
    }

    private Component getUserForm(String userName) {
        Banner banner = bannerService.find(ID, userName);
        Banner adminBanner = getAdmin();
        //will new to go back here and update this to set the labels using admin instead
        VerticalLayout layout = new VerticalLayout();
        rarity1.setLabel(banner.getRarity1());
        rarity1.setValue(banner.getRarity1Value());
        rarity1.setMin(0);
        rarity2.setLabel(banner.getRarity2());
        rarity2.setValue(banner.getRarity2Value());
        rarity2.setMin(0);
        rarity3.setLabel(banner.getRarity3());
        rarity3.setValue(banner.getRarity3Value());
        rarity3.setMin(0);
        layout.add(rarity1, rarity2, rarity3);
        if (getRarities() > 3) {
            rarity4.setLabel(banner.getRarity4());
            rarity4.setValue(banner.getRarity4Value());
            rarity4.setMin(0);
            layout.add(rarity4);
        }
        if (getRarities() > 4) {
            rarity5.setLabel(banner.getRarity5());
            rarity5.setValue(banner.getRarity5Value());
            rarity5.setMin(0);
            layout.add(rarity5);
        }
        if (getRarities() > 5) {
            rarity6.setLabel(banner.getRarity6());
            rarity6.setValue(banner.getRarity6Value());
            rarity6.setMin(0);
            layout.add(rarity6);
        }
        if (getRarities() > 6) {
            rarity7.setLabel(banner.getRarity7());
            rarity7.setValue(banner.getRarity7Value());
            rarity7.setMin(0);
            layout.add(rarity7);
        }
        if (getRarities() > 7) {
            rarity8.setLabel(banner.getRarity8());
            rarity8.setValue(banner.getRarity8Value());
            rarity8.setMin(0);
            layout.add(rarity8);
        }
        if (getRarities() > 8) {
            rarity9.setLabel(banner.getRarity9());
            rarity9.setValue(banner.getRarity9Value());
            rarity9.setMin(0);
            layout.add(rarity9);
        }
        if (getRarities() > 9) {
            rarity10.setLabel(banner.getRarity10());
            rarity10.setValue(banner.getRarity10Value());
            rarity10.setMin(0);
            layout.add(rarity10);
        }
        return layout;
    }

    private void submitData(String userName) {
        Banner banner = bannerService.find(ID, userName);
        banner.setRarity1Value(rarity1.getValue());
        banner.setRarity2Value(rarity2.getValue());
        banner.setRarity3Value(rarity3.getValue());
        if (getRarities() > 3)
            banner.setRarity4Value(rarity4.getValue());
        if (getRarities() > 4)
            banner.setRarity5Value(rarity5.getValue());
        if (getRarities() > 5)
            banner.setRarity6Value(rarity6.getValue());
        if (getRarities() > 6)
            banner.setRarity7Value(rarity7.getValue());
        if (getRarities() > 7)
            banner.setRarity8Value(rarity8.getValue());
        if (getRarities() > 8)
            banner.setRarity9Value(rarity9.getValue());
        if (getRarities() > 9)
            banner.setRarity10Value(rarity10.getValue());
        bannerService.save(banner);
        UI.getCurrent().getPage().reload();
        //will need to iterate through the get rarities like before
        //set the rarity to the new values
        //then need to find out how to update an existing banner
    }

}
