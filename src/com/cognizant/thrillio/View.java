package com.cognizant.thrillio;

import com.cognizant.thrillio.constants.KidFriendlyStatus;
import com.cognizant.thrillio.constants.UserType;
import com.cognizant.thrillio.controllers.BookmarkController;
import com.cognizant.thrillio.entities.Bookmark;
import com.cognizant.thrillio.entities.User;
import com.cognizant.thrillio.partner.Shareable;

import java.util.List;

public class View {

    public static void browse(User user, List<List<Bookmark>> bookmarks) {
        System.out.println("\n" + user.getEmail() + " is browsing ...");

        for (List<Bookmark> bookmarkArr : bookmarks) {
            for (Bookmark bookmark : bookmarkArr) {
                //user browses and makes decision
                boolean isBookmarkSelected = getDecision();
                //if decided, add bookmark
                if (isBookmarkSelected) {
                    BookmarkController.getInstance().saveUserBookmark(user, bookmark);
                    System.out.println("new item bookmarked -- " + bookmark);
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

    //TODO: these simulate user interaction - need to refactor in IO
    private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
        double randomValue = Math.random();
        return randomValue < 0.4 ? KidFriendlyStatus.APPROVED : (randomValue >= 0.4 && randomValue < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getDecision() {
        return Math.random() < 0.5 ? true : false;
    }
}
