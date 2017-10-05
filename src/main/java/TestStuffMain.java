import model.entity.User;
import model.service.UserService;

import java.util.Optional;

public class TestStuffMain {
    public static void main(String[] args) throws Exception {

//        DaoFactory daoFactory = DaoFactory.getInstance();
//        DaoConnection daoConnection = daoFactory.getConnection();
        UserService userService = UserService.getInstance();
        System.out.println(userService.getClientById(7));
//
//        String md5Hex = DigestUtils.md5Hex("7532159");
//
//        try (UserDAO userDAO = daoFactory.createUserDAO(daoConnection)) {
//            Optional<User> result = userDAO.findById(7);
//            System.out.println(result);
//            System.out.println(result.get().getHashPassword().equals(md5Hex));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Optional<User> user = userService.login("asfavgavg", "sadas2159");

        String testString = "/periodicals/main?item_id=5";
        String result = testString.toLowerCase().replaceAll("/periodicals/", "").replaceAll("[?].+", "");
        System.out.println(testString);
        System.out.println(result);
        System.out.println(result.length());


//        userService.createNewUser("hipster","741258963","Samuel","Jackson","hiphop@gmail.com","+38(014)555-40-04");
//        System.out.println(userService.isLoginExists("pRoV"));

//        try (ClientDAO clientDAO = daoFactory.createClientDAO(daoConnection)) {
//            System.out.println(clientDAO.findByEmail("PROVALOV1@gmail.com"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

}
