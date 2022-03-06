package managers;

import entities.Book;
import entities.Movie;
import entities.WebLink;
import org.w3c.dom.html.HTMLImageElement;

/**
 * @author cognizant
 */
public class BookmarkManager {
    private static BookmarkManager bookmarkManager = new BookmarkManager();

    private BookmarkManager() {
    }

    public static BookmarkManager getInstance() {
        return bookmarkManager;
    }

    public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, String genre, double amazonRating) {
        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);

        return book;
    }

    public WebLink createWebLink(long id, String title, String url, String host) {
        WebLink webLink = new WebLink();

        webLink.setId(id);
        webLink.setTitle(title);
        webLink.setUrl(url);
        webLink.setHost(host);

        return webLink;
    }

    public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast, String[] directors, String genre, double imdbRating) {
        Movie movie = new Movie();

        movie.setId(id);
        movie.setTitle(title);
        movie.setProfileUrl(profileUrl);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }
}
