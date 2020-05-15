package com.packagename.myapp.ui;

import com.packagename.myapp.backend.entity.Game;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;

import java.util.List;

@CssImport("./styles/shared-styles.css")
public class GameForm extends FormLayout {

    Binder<Game> binder = new BeanValidationBinder<>(Game.class);
    TextField gameName = new TextField("Game Name");
    TextField costPerSingleRoll = new TextField("Cost Per Single Roll (Real World Cost of One Roll) ");
    TextField igcCostPerSingleRoll = new TextField("Role Cost In IGC (In-game Currency) ");
    TextField gameID = new TextField("Game ID");

    Button save = new Button("Save");
    Button close = new Button("Cancel");

    public GameForm(List<Game> games) {
        addClassName("game-form");
        binder.forField(gameName).bind(Game::getGameName, Game::setGameName);
        binder.forField(gameID).bind(Game::getGameID,Game::setGameID);
        binder.forField(costPerSingleRoll)
                .withConverter(
                        new StringToDoubleConverter("Not a number"))
                .bind(Game::getCostPerSingleRoll,
                        Game::setCostPerSingleRoll);
        binder.forField(igcCostPerSingleRoll)
                .withConverter(
                        new StringToDoubleConverter("Not a number"))
                .bind(Game::getCostPerIGC,
                        Game::setCostPerIGC);

        add(gameName, costPerSingleRoll, igcCostPerSingleRoll, gameID, createButtonsLayout());
    }
 /*
    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, close);
    }

    public void setGame(Game game) {
        binder.setBean(game);
    }
*/
    private Component createButtonsLayout() {
        // omitted
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        save.addClickListener(event -> validateAndSave());
        //delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));


        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public void setGame(Game game) {
        binder.setBean(game);
    }

    // Events
    public static abstract class GameFormEvent extends ComponentEvent<GameForm> {
        private Game game;

        protected GameFormEvent(GameForm source, Game game) {
            super(source, false);
            this.game = game;
        }

        public Game getGame() {
            return game;
        }
    }

    public static class SaveEvent extends GameFormEvent {
        SaveEvent(GameForm source, Game game) {
            super(source, game);
        }
    }

  //Delete event isn't needed for the moment
  public static class DeleteEvent extends GameFormEvent {
      DeleteEvent(GameForm source, Game game) {
          super(source, game);
      }
  }

    public static class CloseEvent extends GameFormEvent {
        CloseEvent(GameForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}


