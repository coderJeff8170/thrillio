package com.cognizant.thrillio.controllers;

import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.User;
import com.cognizant.thrillio.managers.BookmarkManager;

public class BookmarkController {
    private static BookmarkController instance = new BookmarkController();

    private BookmarkController() {}

    public static BookmarkController getInstance() {
        return instance;
    }

    public void saveUserBookmark(User user,Bookmark bookmark) {
        //remember a manager is same as a service
        BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
    }
}
