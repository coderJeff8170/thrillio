package com.cognizant.thrillio;

import com.cognizant.thrillio.constants.*;
import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.User;
import com.cognizant.thrillio.entities.UserBookmark;
import com.cognizant.thrillio.managers.BookmarkManager;
import com.cognizant.thrillio.managers.UserManager;
import com.cognizant.thrillio.util.IOUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.cognizant.thrillio.constants.EnvironmentVariables.*;

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
        /*loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();*/

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //new com.mysql.jdbc.Driver();
                    //OR
            //System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
                    //OR
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //try-with-resources ==> conn and stmt will be close automagically
        //Connection string format: <protocol>:<sub-protocol>:<data-source details>
        try(Connection conn = DriverManager.getConnection(THRILLIO_CONNECTION_STRING, USER, PASSWORD);
            Statement stmt = conn.createStatement()) {

            loadUsers(stmt);
            loadWebLinks(stmt);
            loadMovies(stmt);
            loadBooks(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loadMovies(Statement stmt) throws SQLException {
        String query = "Select m.id, title, release_year, GROUP_CONCAT(DISTINCT a.name SEPARATOR ',') AS cast, GROUP_CONCAT(DISTINCT d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
                + " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
                + "where m.id = ma.movie_id and ma.actor_id = a.id and "
                + "m.id = md.movie_id and md.director_id = d.id group by m.id";
        ResultSet rs = stmt.executeQuery(query);

        List<Bookmark> bookmarkList = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int releaseYear = rs.getInt("release_year");
            String[] cast = rs.getString("cast").split(",");
            String[] directors = rs.getString("directors").split(",");
            int genre_id = rs.getInt("movie_genre_id");
            MovieGenre genre = MovieGenre.values()[genre_id];
            double imdbRating = rs.getDouble("imdb_rating");

            Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, "", releaseYear, cast, directors, genre, imdbRating/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    private static void loadBooks(Statement stmt) throws SQLException {
        String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
                + " from Book b, Publisher p, Author a, Book_Author ba "
                + "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
        ResultSet rs = stmt.executeQuery(query);

        List<Bookmark> bookmarkList = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int publicationYear = rs.getInt("publication_year");
            String publisher = rs.getString("name");
            String[] authors = rs.getString("authors").split(",");
            int genre_id = rs.getInt("book_genre_id");
            BookGenre genre = BookGenre.values()[genre_id];
            double amazonRating = rs.getDouble("amazon_rating");

            Date createdDate = rs.getDate("created_date");
            System.out.println("createdDate: " + createdDate);
            Timestamp timeStamp = rs.getTimestamp(8);
            System.out.println("timeStamp: " + timeStamp);
            System.out.println("localDateTime: " + timeStamp.toLocalDateTime());

            System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);

            Bookmark bookmark = BookmarkManager.getInstance().createBook(id, title, publicationYear, publisher, authors, genre, amazonRating/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    private static void loadUsers(Statement stmt) throws SQLException {

        String query = "Select * from User";
        ResultSet rs = stmt.executeQuery(query);

//        List<String> data = new ArrayList<>();
        while(rs.next()) {
            long id = rs.getLong("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int gender_id = rs.getInt("gender_id");
            Gender gender = Gender.values()[gender_id];
            int user_type_id = rs.getInt("user_type_id");
            UserType userType = UserType.values()[user_type_id];


            User user = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender,
                    userType);
            users.add(user);
        }

    }

    private static void loadWebLinks(Statement stmt) throws SQLException {
        String query = "SELECT * FROM weblink";
        ResultSet rs = stmt.executeQuery(query);
//        List<String> data = new ArrayList<>();
//        IOUtil.read(data, "WebLink");

        List<Bookmark> bookmarkList = new ArrayList<>();
//        for (String row : data) {
//            String[] values = row.split("\t");
//            Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1], values[2], values[3]/*, values[4]*/);
//            bookmarkList.add(bookmark);
//        }

        while(rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            String url = rs.getString("url");
            String host = rs.getString("host");
            int kid_friendly_status = rs.getInt("kid_friendly_status");
            KidFriendlyStatus kidFriendlyStatus = KidFriendlyStatus.values()[kid_friendly_status];
            Date createdDate = rs.getDate("created_date");


            Bookmark bookmark = BookmarkManager.getInstance().createWebLink(id, title, url, host/*, values[4]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    private static void loadUsers() {

        List<String> data = new ArrayList<>();
        IOUtil.read(data, "User");

        for (String row : data) {
            String[] values = row.split("\t");

            Gender gender = Gender.MALE;
            if (values[5].equals("f")) {
                gender = Gender.FEMALE;
            } else if (values[5].equals("t")) {
                gender = Gender.TRANSGENDER;
            }

            User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, UserType.valueOf(values[6]));
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
            Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "", Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5]), Double.parseDouble(values[6])/*, values[7]*/);
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
            Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5]), Double.parseDouble(values[6])/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks.add(userBookmark);
    }
}
