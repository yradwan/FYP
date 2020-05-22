package com.packagename.myapp.ui.LoginView;

import com.packagename.myapp.backend.entity.User;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class LoginRegistrationForm extends FormLayout {

    Binder<User> binder = new BeanValidationBinder<>(User.class);
    TextField userName = new TextField("Username");
    TextField password = new TextField("Password");

    Button save = new Button("Save");
    Button close = new Button("Cancel");

    public LoginRegistrationForm(List<User> users) {
        binder.bindInstanceFields(this);
        addClassName("registration-form");

        save.addClickListener(event -> validateAndSave());
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        
        add(userName, password, createButtonsLayout());
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, close);
    }

    public void setUser(User user) {
        binder.setBean(user);
    }

    // Events
    public static abstract class LoginFormEvent extends ComponentEvent<LoginRegistrationForm> {
        private User user;

        protected LoginFormEvent(LoginRegistrationForm source, User user) {
            super(source, false);
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    public static class SaveEvent extends LoginFormEvent {
        SaveEvent(LoginRegistrationForm source, User user) {
            super(source, user);
        }
    }
/*
    public static class DeleteEvent extends LoginFormEvent {
        DeleteEvent(LoginRegistrationForm source, User user) {
            super(source, user);
        }

    }
 */
    public static class CloseEvent extends LoginFormEvent {
        CloseEvent(LoginRegistrationForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
