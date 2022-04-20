package com.cognizant.thrillio;

import com.cognizant.thrillio.constants.Gender;
import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.User;
import com.cognizant.thrillio.entities.UserBookmark;
import com.cognizant.thrillio.managers.BookmarkManager;
import com.cognizant.thrillio.managers.UserManager;
import com.cognizant.thrillio.util.IOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cognizant
 */
public class DataStore {

    private static List<User> users = new ArrayList<>();
    public static List<User> getUsers() {
        return users;
    }
    public static List<List<Bookmark>> bookmarks = new ArrayList<>();
    public static List<List<Bookmark>> getBookmarks() {
        return bookmarks;
    }
    private static List<UserBookmark> userBookmarks = new ArrayList<>();

    public static void loadData() {
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    private static void loadUsers() {

        List<String> data = new ArrayList<>();
        IOUtil.read(data, "User");

        for (String row : data) {
            String[] values = row.split("\t");

            int gender = Gender.MALE;
            if (values[5].equals("f")) {
                gender = Gender.FEMALE;
            } else if (values[5].equals("t")) {
                gender = Gender.TRANSGENDER;
            }

            User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]);
            users.add(user);
        }
    }

        private static void loadWebLinks() {

        List<String> data = new ArrayList<>();
        IOUtil.read(data, "WebLink");

        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1], values[2], values[3]/*, values[4]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

        private static void loadMovies() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "Movie");

        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            String[] cast = values[3].split(",");
            String[] directors = values[4].split(",");
            Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "", Integer.parseInt(values[2]), cast, directors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

        private static void loadBooks() {

        List<String> data = new ArrayList<>();
        IOUtil.read(data, "Book");

        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            String[] authors = values[4].split(",");
            Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], authors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks.add(userBookmark);
    }
}
