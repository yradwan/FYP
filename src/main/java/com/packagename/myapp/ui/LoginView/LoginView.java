package com.packagename.myapp.ui.LoginView;

import ch.qos.logback.core.Layout;
import com.packagename.myapp.backend.entity.User;
import com.packagename.myapp.backend.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route("login")
@PageTitle("Login")
@CssImport("./styles/shared-styles.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login = new LoginForm();
    private LoginRegistrationForm register;
    private UserService userService;

    public LoginView(UserService userService){
        this.userService = userService;
        register = new LoginRegistrationForm(userService.findAll());
        register.addListener(LoginRegistrationForm.SaveEvent.class, this::saveUser);
        register.addListener(LoginRegistrationForm.CloseEvent.class, e -> closeEditor());
        addClassName("login-view");

        login.setAction("login");
        Button registerButton = new Button("Register");
        registerButton.addClickListener(click -> addUser());

        VerticalLayout loginLayout = new VerticalLayout(new H1("Luck Tracker"),login, registerButton, register);
        loginLayout.addClassName("login-layout");
        loginLayout.setSizeFull();
        loginLayout.setAlignItems(Alignment.CENTER);
        loginLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        add(loginLayout);
        closeEditor();
    }

    void addUser() {
        editUser(new User());
    }

    private void saveUser(LoginRegistrationForm.SaveEvent event) {
        userService.save(event.getUser());
        closeEditor();
        UI.getCurrent().getPage().reload();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // inform the user about an authentication error
        if(event.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

    private void closeEditor() {
        register.setUser(null);
        register.setVisible(false);
        removeClassName("editing");
    }

    public void editUser(User user) {
        if (user == null) {
            closeEditor();
        } else {
            register.setUser(user);
            register.setVisible(true);
            addClassName("editing");
        }
    }
}
