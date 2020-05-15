package com.packagename.myapp.ui;

import com.packagename.myapp.backend.entity.Game;
import com.packagename.myapp.backend.service.GameService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private GameService gameService;
    private Grid<Game> grid = new Grid<>(Game.class);
    private TextField filterText = new TextField();
    private GameForm gameForm;

    public MainView(GameService gameService) {
        this.gameService = gameService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();

        gameForm = new GameForm(gameService.findAll());
        gameForm.addListener(GameForm.SaveEvent.class, this::saveGame);
        gameForm.addListener(GameForm.DeleteEvent.class, this::deleteGame);
        gameForm.addListener(GameForm.CloseEvent.class, e -> closeEditor());

        Div div = new Div(grid, gameForm);
        div.addClassName("div");
        div.setSizeFull();

        add(getToolbar(), div);
        updateList();
        closeEditor();
    }

    private void saveGame(GameForm.SaveEvent event) {
        gameService.save(event.getGame());
        updateList();
        closeEditor();
    }

    private void deleteGame(GameForm.DeleteEvent event) {
        gameService.delete(event.getGame());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        gameForm.setGame(null);
        gameForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by game name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addGameButton = new Button("Add Game");
        addGameButton.addClickListener(click -> addGame());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addGameButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addGame() {
        grid.asSingleSelect().clear();
        editGame(new Game());
    }

    private void updateList() {
        grid.setItems(gameService.findAll(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassName("game-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.setColumns("gameName", "costPerSingleRoll", "costPerIGC");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editGame(event.getValue()));
    }

    private void editGame(Game game) {
        if (game == null) {
            closeEditor();
        } else {
            gameForm.setGame(game);
            gameForm.setVisible(true);
            addClassName("editing");
        }
    }

}