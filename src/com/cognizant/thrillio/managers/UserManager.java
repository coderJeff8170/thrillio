package com.cognizant.thrillio.managers;

import com.cognizant.thrillio.constants.Gender;
import com.cognizant.thrillio.constants.UserType;
import com.cognizant.thrillio.dao.UserDao;
import com.cognizant.thrillio.entities.User;

import java.util.List;

/**
 * @author cognizant
 */
public class UserManager {
    private static UserManager userManager = new UserManager();
    private static UserDao dao = new UserDao();

    private UserManager() {}

    public static UserManager getInstance() {
        return userManager;
    }

    public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender,
                           UserType userType) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setUserType(userType);

        return user;
    }

    public List<User> getUsers() {
        return dao.getUsers();
    }

}
