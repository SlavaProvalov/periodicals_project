package model.service;

import exceptions.EmailAlreadyExistException;
import exceptions.InvalidPasswordException;
import exceptions.LoginAlreadyExistException;
import exceptions.UserNotFoundException;
import model.dao.ClientDAO;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDAO;
import model.entity.Client;
import model.entity.User;
import model.entity.builder.ClientBuilder;
import model.entity.builder.UserBuilder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by Provalov on 25.09.2017.
 */
public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private UserService() {
    }

    private static class Holder {
        static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<User> login(String login, String password) throws UserNotFoundException, InvalidPasswordException, SQLException {
        Optional<User> userOptional;
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            UserDAO dao = daoFactory.createUserDAO(connection);
            String hashPass = MD5.md5Hex(password);
            userOptional = Optional.ofNullable(dao.findByLogin(login).orElseThrow(UserNotFoundException::new));
            userOptional = Optional.ofNullable(userOptional.filter(user -> hashPass.equals(user.getHashPassword())).orElseThrow(InvalidPasswordException::new));
            connection.commit();
        }
        return userOptional;
    }

    public Optional<Client> getClientById(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ClientDAO clientDAO = daoFactory.createClientDAO(connection);
            return clientDAO.findById(id);
        }
    }

    private boolean isLoginExists(String login) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDAO dao = daoFactory.createUserDAO(connection);
            return dao.findByLogin(login).isPresent();
        }
    }

    private boolean isEmailExists(String email) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ClientDAO dao = daoFactory.createClientDAO(connection);
            return dao.findByEmail(email.toLowerCase()).isPresent();
        }
    }

    public int createNewUser(String login, String password, String firstName, String lastName, String email, String phoneNumber) throws LoginAlreadyExistException, EmailAlreadyExistException, SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            if (isLoginExists(login)) {
                throw new LoginAlreadyExistException();
            } else if (isEmailExists(email)) {
                throw new EmailAlreadyExistException();
            } else {
                UserDAO userDAO = daoFactory.createUserDAO(connection);
                ClientDAO clientDAO = daoFactory.createClientDAO(connection);
                UserBuilder userBuilder = new UserBuilder();
                int id = userDAO.insert(userBuilder.createNewUser()
                        .setLogin(login)
                        .setPassword(MD5.md5Hex(password))
                        .setRole("user").getUser());
                ClientBuilder clientBuilder = new ClientBuilder();
                clientDAO.insert(clientBuilder
                        .createNewClient()
                        .setId(id)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setPhone(phoneNumber)
                        .setSignUpDate(LocalDate.now()).getClient());
                connection.commit();
                return id;
            }
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDAO dao = daoFactory.createUserDAO(connection);
            return dao.delete(id);
        }
    }

    public void updateUser(int id, String login, String password, String role, String firstName, String lastName,
                           String email, boolean checkEmail, String phone) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDAO userDAO = daoFactory.createUserDAO(connection);
            ClientDAO clientDAO = daoFactory.createClientDAO(connection);
            UserBuilder userBuilder = new UserBuilder();
            ClientBuilder clientBuilder = new ClientBuilder();
            connection.begin();
            if (checkEmail && isEmailExists(email)) {
                throw new EmailAlreadyExistException();
            }
            userDAO.update(userBuilder
                    .createNewUser()
                    .setId(id)
                    .setLogin(login)
                    .setPassword(MD5.md5Hex(password))
                    .setRole(role)
                    .getUser());
            clientDAO.update(clientBuilder
                    .createNewClient()
                    .setId(id)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setPhone(phone)
                    .getClient());
            connection.commit();
        }
    }
}
