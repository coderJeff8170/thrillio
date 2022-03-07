package com.cognizant.thrillio.dao;

import com.cognizant.thrillio.DataStore;
import com.cognizant.thrillio.entities.Bookmark;

/**
 * @author cognizant
 */
public class BookmarkDao {
    public Bookmark[][] getBookmarks() {
        return DataStore.getBookmarks();
    }
}
