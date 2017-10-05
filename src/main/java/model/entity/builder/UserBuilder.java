package model.entity.builder;

import model.entity.Role;
import model.entity.User;

/**
 * Created by Provalov on 25.09.2017.
 */
public class UserBuilder {
    public static User createSimpleUser(String login, String password) {
        return new User(login, password, Role.USER);
    }
}
