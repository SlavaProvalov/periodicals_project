package model.service;

import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;
import model.entity.Role;
import model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UserServiceTest extends Mockito {
    UserService service = UserService.getInstance();

    @Test(expected = InvalidPasswordException.class)
    public void login_withWrongPass() throws Exception {
        service.login("prov", "000000");
    }

    @Test(expected = UserNotFoundException.class)
    public void login_withWrongLogin() throws Exception {
        service.login("qwerty_*123", "1234567");
    }

    @Test(expected = NoSuchElementException.class)
    public void login_nullTest() throws Exception {
        service.login(null, null);
    }

    @Test
    public void login_normal() throws Exception {
        Optional<User> user = service.login("nixon", "1234567");
        Assert.assertEquals(user.get().getRole(), Role.USER);
        Assert.assertEquals(user.get().getLogin(), "nixon");
        Assert.assertEquals(user.get().getHashPassword(), MD5.md5Hex("1234567"));
    }
    
}