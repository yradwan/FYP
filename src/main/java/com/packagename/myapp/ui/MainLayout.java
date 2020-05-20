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
public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Luck Tracker");
        logo.addClassName("logo");
        Anchor logout = new Anchor("/logout", "Log Out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.expand(logo);
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", GameListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink));
    }
}