package com.cognizant.thrillio.dao;


import com.cognizant.thrillio.DataStore;
import com.cognizant.thrillio.entities.User;

import java.util.List;

/**
 * @author cognizant
 */
public class UserDao {
    public List<User> getUsers() {
        return DataStore.getUsers();
    }
}
