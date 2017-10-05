package model.entity;

/**
 * Created by Provalov on 21.09.2017.
 */
public class User {
    private int id;
    private String login;
    private String hashPassword;
    private Role role;


    public User(int id, String login, String hashPassword, String role) {
        this.id = id;
        this.login = login;
        this.hashPassword = hashPassword;
        this.role = "admin".equals(role) ? Role.ADMIN : Role.USER;

    }

    public User(String login, String hashPassword, Role role) {
        this.login = login;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        return getLogin().equals(user.getLogin());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getLogin().hashCode();
        return result;
    }
}
