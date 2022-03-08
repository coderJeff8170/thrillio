package com.cognizant.thrillio;

import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.User;
import com.cognizant.thrillio.managers.BookmarkManager;
import com.cognizant.thrillio.managers.UserManager;

import javax.xml.crypto.Data;

/**
 * @author cognizant
 */
public class Launch {
    private static User[] users;
    private static Bookmark[][] bookmarks;

    private static void loadData() {
        System.out.println("1. loading data ...");
        DataStore.loadData();
        users = UserManager.getInstance().getUsers();
        bookmarks = BookmarkManager.getInstance().getBookmarks();

        System.out.println("printing data ...");
        printBookmarkData();
        printUserData();
    }

    private static void printUserData() {
        for(User user : users) {
            System.out.println(user);
        }
    }

    private static void printBookmarkData() {
        for(Bookmark[] bookmarks : bookmarks) {
            for(Bookmark bookmark : bookmarks) {
                System.out.println(bookmark);
            }
        }
    }

    private static void startBookmarking() {
        System.out.println("\n2. Bookmarking ...");
        //invoke bookmark method for all users
        for(User user : users) {
            View.bookmark(user, bookmarks);
        }
    }


    public static void main(String[] args) {

        loadData();
        startBookmarking();
    }




}
