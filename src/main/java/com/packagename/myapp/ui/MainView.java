package com.packagename.myapp.ui;

import com.packagename.myapp.backend.entity.Game;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private Grid<Game> grid = new Grid<>(Game.class);

    public MainView() {
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
    }

    private void configureGrid() {
        grid.addClassName("game-grid");
        grid.setSizeFull();
        grid.setColumns("gameName", "costPerSingleRoll", "costPerIGC");

    }

}