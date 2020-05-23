package com.packagename.myapp.ui.gameListView;

import com.packagename.myapp.backend.entity.Banner;
import com.packagename.myapp.backend.entity.Game;
import com.packagename.myapp.backend.service.BannerService;
import com.packagename.myapp.backend.service.GameService;
import com.packagename.myapp.ui.HomeLayout;
import com.packagename.myapp.ui.bannerView.BannerView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.List;

@Route(value="", layout = HomeLayout.class)
@PageTitle("List of Games")
public class GameListView extends VerticalLayout {

    private BannerService bannerService;
    private GameService gameService;
    private Grid<Game> grid = new Grid<>(Game.class);
    //private Grid<Banner> grid = new Grid<>(Banner.class);
    private VerticalLayout rarities = new VerticalLayout();
    private TextField filterText = new TextField();
    private TextField rarity1 = new TextField("Rarity Name");
    private TextField rarity2 = new TextField("Rarity Name");
    private TextField rarity3 = new TextField("Rarity Name");
    private TextField rarity4 = new TextField("Rarity Name");
    private TextField rarity5 = new TextField("Rarity Name");
    private TextField rarity6 = new TextField("Rarity Name");
    private TextField rarity7 = new TextField("Rarity Name");
    private TextField rarity8 = new TextField("Rarity Name");
    private TextField rarity9 = new TextField("Rarity Name");
    private TextField rarity10 = new TextField("Rarity Name");
    private ComboBox<String> comboBox = new ComboBox<>();

    private GameForm gameForm;
    private RouterLink routerLink;
    //private Button button;
    private String pathVariable;
    private int raritiesCount = 0;
    private boolean linkCheck = true;

    public GameListView(GameService gameService, BannerService bannerService) {
        this.gameService = gameService;
        this.bannerService = bannerService;
        addClassName("list-view");
        setSizeFull();
        comboBox.setItems("3", "4", "5", "6", "7", "8", "9", "10");
        comboBox.setPlaceholder("Select Number of Rarities");
        comboBox.setVisible(false);
        configureGrid();
        comboBox.addValueChangeListener(e -> rarities());
        gameForm = new GameForm(gameService.findAll());
        gameForm.addListener(GameForm.SaveEvent.class, this::saveGame);
        gameForm.addListener(GameForm.DeleteEvent.class, this::deleteGame);
        gameForm.addListener(GameForm.CloseEvent.class, e -> closeEditor());
        gameForm.add(comboBox);
        rarities.add(rarity1, rarity2, rarity3, rarity4, rarity5, rarity6, rarity7, rarity8, rarity9, rarity10);
        gameForm.add(rarities);
        setRaritiesNotVisible();
        Div div = new Div(grid, gameForm);
        div.addClassName("div");
        div.setSizeFull();

        add(getToolbar(), div);
        updateList();
        closeEditor();
    }

    private void setRaritiesNotVisible() {
        rarity1.setVisible(false);
        rarity2.setVisible(false);
        rarity3.setVisible(false);
        rarity4.setVisible(false);
        rarity5.setVisible(false);
        rarity6.setVisible(false);
        rarity7.setVisible(false);
        rarity8.setVisible(false);
        rarity9.setVisible(false);
        rarity10.setVisible(false);
    }

    private void clearRarities(){
        rarity1.clear();
        rarity2.clear();
        rarity3.clear();
        rarity4.clear();
        rarity5.clear();
        rarity6.clear();
        rarity7.clear();
        rarity8.clear();
        rarity9.clear();
        rarity10.clear();
    }

    private void setRaritiesVisible(){
        clearRarities();
        rarity1.setVisible(true);
        rarity2.setVisible(true);
        rarity3.setVisible(true);
        if (raritiesCount > 3 )
            rarity4.setVisible(true);
        if (raritiesCount > 4)
            rarity5.setVisible(true);
        if (raritiesCount > 5)
            rarity6.setVisible(true);
        if (raritiesCount > 6)
            rarity7.setVisible(true);
        if (raritiesCount > 7)
            rarity8.setVisible(true);
        if (raritiesCount > 8)
            rarity9.setVisible(true);
        if (raritiesCount > 9)
            rarity10.setVisible(true);
    }

    private void rarities(){
        raritiesCount = Integer.parseInt(comboBox.getValue());
        comboBox.setVisible(false);
        setRaritiesVisible();
    }

    private void saveGame(GameForm.SaveEvent event) {
        String check = event.getGame().getGameID();
        List<Banner> bannerList = bannerService.findAll(check);
        //could potentially add some extra checks on the form to make sure the regular
        //form editing while not allowed duplicate
        if (bannerList.size() == 0){
            Banner banner = new Banner();
            banner.setRarity1(rarity1.getValue());
            banner.setRarity2(rarity2.getValue());
            banner.setRarity3(rarity3.getValue());
            if (raritiesCount > 3 )
                banner.setRarity4(rarity4.getValue());
            if (raritiesCount > 4)
                banner.setRarity5(rarity5.getValue());
            if (raritiesCount > 5)
                banner.setRarity6(rarity6.getValue());
            if (raritiesCount > 6)
                banner.setRarity7(rarity7.getValue());
            if (raritiesCount > 7)
                banner.setRarity8(rarity8.getValue());
            if (raritiesCount > 8)
                banner.setRarity9(rarity9.getValue());
            if (raritiesCount > 9)
                banner.setRarity10(rarity10.getValue());
            gameForm.remove(rarities);
            if (bannerCheck(banner) == false){
                Notification notification = new Notification("Game not added, you did not properly fill the form",
                        10500);
                notification.open();
            }
            else {
                banner.setUserName("admin");
                banner.setGameID(gameForm.gameID.getValue());
                bannerService.save(banner);
                gameService.save(event.getGame());
            }
        }
        else
            gameService.save(event.getGame());
        updateList();
        closeEditor();
    }

    private boolean bannerCheck(Banner banner) {
        boolean bool = true;
        if (banner.getRarity1() == "")
            bool = false;
        if (banner.getRarity2() == "")
            bool = false;
        if (banner.getRarity3() == "")
            bool = false;
        if (raritiesCount > 3 )
            if (banner.getRarity4() == "")
                bool = false;
        if (raritiesCount > 4)
            if (banner.getRarity5() == "")
                bool = false;
        if (raritiesCount > 5)
            if (banner.getRarity6() == "")
                bool = false;
        if (raritiesCount > 6)
            if (banner.getRarity7() == "")
                bool = false;
        if (raritiesCount > 7)
            if (banner.getRarity8() == "")
                bool = false;
        if (raritiesCount > 8)
            if (banner.getRarity9() == "")
                bool = false;
        if (raritiesCount > 9)
            if (banner.getRarity10() == "")
                bool = false;
        return bool;
    }

    private void deleteGame(GameForm.DeleteEvent event) {
        gameService.delete(event.getGame());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        gameForm.setGame(null);
        setRaritiesNotVisible();
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

    private void addGame() {
        grid.asSingleSelect().clear();
        editGame(new Game());
        comboBox.setVisible(true);
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
            setRaritiesNotVisible();
            if (linkCheck == false) {
                gameForm.remove(routerLink);
                linkCheck = true;
            }
            if (game.getGameID() != ""){
                pathVariable = gameForm.gameID.getValue();
                //button = new Button("Link to game data");
                comboBox.setVisible(false);
                routerLink = new RouterLink("Link to game data", BannerView.class, pathVariable);
                gameForm.add(routerLink);
                linkCheck = false;
            }
            addClassName("editing");
        }
    }
}