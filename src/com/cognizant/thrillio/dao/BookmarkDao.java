package com.cognizant.thrillio.dao;

import com.cognizant.thrillio.DataStore;
import com.cognizant.thrillio.entities.Book;
import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.UserBookmark;

import java.util.List;

/**
 * @author cognizant
 */
public class BookmarkDao {
    public List<List<Bookmark>> getBookmarks() {
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {
        DataStore.add(userBookmark);
    }
}
