package com.cognizant.thrillio;

import com.cognizant.thrillio.bgjobs.WebpageDownloaderTask;
import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.User;
import com.cognizant.thrillio.managers.BookmarkManager;
import com.cognizant.thrillio.managers.UserManager;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * @author cognizant
 */
public class Launch {
    private static List<User> users;
    private static List<List<Bookmark>> bookmarks;

    private static void loadData() {
        System.out.println("1. loading data ...");
        DataStore.loadData();

        users = UserManager.getInstance().getUsers();
        bookmarks = BookmarkManager.getInstance().getBookmarks();
    }

    private static void printUserData() {
        for(User user : users) {
            System.out.println(user);
        }
    }

    private static void printBookmarkData() {
        for(List<Bookmark> bookmarks : bookmarks) {
            for(Bookmark bookmark : bookmarks) {
                System.out.println(bookmark);
            }
        }
    }

    private static void start() {
        //invoke bookmark method for all users
        for(User user : users) {
            View.browse(user, bookmarks);
        }
    }

    public static void main(String[] args) {
        loadData();
        start();

        //background jobs
        runDownloaderJob();
    }

    private static void runDownloaderJob() {
        WebpageDownloaderTask task = new WebpageDownloaderTask(true);
        (new Thread(task)).start();
    }


}
