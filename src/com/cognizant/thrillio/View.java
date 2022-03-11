package com.cognizant.thrillio;

import com.cognizant.thrillio.constants.KidFriendlyStatus;
import com.cognizant.thrillio.constants.UserType;
import com.cognizant.thrillio.controllers.BookmarkController;
import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.User;
import com.cognizant.thrillio.partner.Shareable;

public class View {
    //    public static void bookmark(User user, Bookmark[][] bookmarks) {
//        System.out.println("\n" + user.getEmail() + " is bookmarking");
//        //each user can have 5 bookmarks
//        for(int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {
//            //randomly select five bookmarks from 3 types x 5 example bookmarks (15 total)
//            int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
//            int bookmarkOffset = (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
//
//            //store it somewhere
//            Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
//            //TODO: if same as any other bookmark in list, get another bookmark
//            BookmarkController.getInstance().saveUserBookmark(user, bookmark);
//            System.out.println(bookmark);
//        }
//    }
    public static void browse(User user, Bookmark[][] bookmarks) {
        System.out.println("\n" + user.getEmail() + " is browsing ...");
        //iterate thru the bookmarks
        int bookmarkCount = 0;
        for (Bookmark[] bookmarkArr : bookmarks) {
            for (Bookmark bookmark : bookmarkArr) {
                //user browses and makes decision
                if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
                    //if less than allowed bookmarks, decide
                    boolean isBookmarkSelected = getDecision();
                    //if decided,  add bookmark and increment count
                    if (isBookmarkSelected) {
                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);
                        System.out.println("new item bookmarked -- " + bookmark);
                        bookmarkCount++;
                    }
                    //mark as kid friendly - only editor and chief editor can do this
                    if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
                        if (bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
                            String kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
                            if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
                                BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
                            }
                        }
                        //if approved, share kid friendly bookmark
                        if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
                                && bookmark instanceof Shareable) {
                            boolean isShared = getDecision();
                            if (isShared) {
                                BookmarkController.getInstance().share(user, bookmark);
                            }
                        }
                    }
                }
            }
        }

    }

    //TODO: these simulate user interaction - need to refactor in IO
    private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
        double randomValue = Math.random();
        return randomValue < 0.4 ? KidFriendlyStatus.APPROVED : (randomValue >= 0.4 && randomValue < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getDecision() {
        return Math.random() < 0.5 ? true : false;
    }
}
