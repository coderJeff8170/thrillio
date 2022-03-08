package com.cognizant.thrillio.dao;

import com.cognizant.thrillio.DataStore;
import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.UserBookmark;

/**
 * @author cognizant
 */
public class BookmarkDao {
    public Bookmark[][] getBookmarks() {
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {
        DataStore.add(userBookmark);
    }
}
