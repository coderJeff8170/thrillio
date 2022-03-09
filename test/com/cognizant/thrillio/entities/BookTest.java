package com.cognizant.thrillio.entities;

import com.cognizant.thrillio.constants.BookGenre;
import com.cognizant.thrillio.managers.BookmarkManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    Book book;
    boolean isKidFriendlyEligible;
    //if
    @Test
    public void isKidFriendlyEligible_shouldReturnFalse_ifGenreContainsPhilosophy() {
        book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY, 4.3);
        isKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse("for philosophy in genre, isKidFriendlyEligible must return false", isKidFriendlyEligible);
    }

    @Test
    public void isKidFriendlyEligible_shouldReturnFalse_ifGenreContainsSelfHelp() {
        book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.SELF_HELP, 4.3);
        isKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse("for \"self help\" in genre, isKidFriendlyEligible must return false", isKidFriendlyEligible);

    }
}