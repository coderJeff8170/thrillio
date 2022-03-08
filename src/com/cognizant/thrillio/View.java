package com.cognizant.thrillio;

import com.cognizant.thrillio.controllers.BookmarkController;
import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.User;

public class View {
    public static void bookmark(User user, Bookmark[][] bookmarks) {
        System.out.println("\n" + user.getEmail() + " is bookmarking");
        //each user can have 5 bookmarks
        for(int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {
            //randomly select five bookmarks from 3 types x 5 example bookmarks (15 total)
            int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
            int bookmarkOffset = (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);

            //store it somewhere
            Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
            //TODO: if same as any other bookmark in list, get another bookmark
            BookmarkController.getInstance().saveUserBookmark(user, bookmark);
            System.out.println(bookmark);
        }
    }
}
