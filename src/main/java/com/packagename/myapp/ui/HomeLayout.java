package com.packagename.myapp.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.packagename.myapp.ui.gameListView.GameListView;
import com.vaadin.flow.server.PWA;

import javax.swing.text.html.ListView;
@PWA(
        name = "Luck Tracker",
        shortName = "Luck",
        offlineResources = {
                "./styles/offline.css",
                "./images/offline.png"}
)
@CssImport("./styles/shared-styles.css")
public class HomeLayout extends AppLayout {
    public HomeLayout() {
        createHomeBar();
    }

    private void createHomeBar() {
        H1 title = new H1("Luck Tracker");
        title.addClassName("title");
        RouterLink games = new RouterLink("List of Games", GameListView.class);
        //this is meant to logout the user
        //this is correct according to official examples but it's not working for some reason
        Anchor logout = new Anchor("logout", "Log Out");
        HorizontalLayout homeBar = new HorizontalLayout( title, games, logout);
        homeBar.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        homeBar.setWidth("100%");
        homeBar.expand(games);
        homeBar.addClassName("home");
        addToNavbar(homeBar);
    }

}