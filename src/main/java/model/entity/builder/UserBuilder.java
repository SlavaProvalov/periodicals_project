package model.entity.builder;

import model.entity.Role;
import model.entity.User;

/**
 * Created by Provalov on 25.09.2017.
 */
public class UserBuilder {
    private User user;

    public UserBuilder createNewUser() {
        user = new User();
        return this;
    }

    public UserBuilder setId(int id) {
        user.setId(id);
        return this;
    }

    public UserBuilder setLogin(String login) {
        user.setLogin(login);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setHashPassword(password);
        return this;
    }

    public UserBuilder setRole(String role) {
        user.setRole("admin".equalsIgnoreCase(role) ? Role.ADMIN : Role.USER);
        return this;
    }

    public User getUser() {
        return user;
    }
}
