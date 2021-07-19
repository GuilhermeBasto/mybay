package ejb;

import data.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserEJBRemote {
    User login(String email, String password);

    boolean checkEmailExists(String email);

    void createAccount(String name, String country, String email, String password);

    User editAccount(String id, String name, String email, String country, String password);

    void deleteAccount(int id);

    List<User> getAllUsers();
}
