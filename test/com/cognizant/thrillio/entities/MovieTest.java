package com.cognizant.thrillio.entities;

import com.cognizant.thrillio.constants.MovieGenre;
import com.cognizant.thrillio.managers.BookmarkManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {
    Movie movie;
    boolean isKidFriendlyEligible;

    //if the movie is either a horror or a thriller, ikf should return false
    @Test
    public void isKidFriendlyEligible_shouldReturnFalse_ifGenreisHorror() {
        movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles","Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.HORROR, 8.5);
        isKidFriendlyEligible = movie.isKidFriendlyEligible();
        assertFalse("for Horror in genre, isKidFriendlyEligible should return false", isKidFriendlyEligible);
    }

    @Test
    public void isKidFriendlyEligible_shouldReturnFalse_ifGenreisThrillers() {
        movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles","Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.THRILLERS, 8.5);
        isKidFriendlyEligible = movie.isKidFriendlyEligible();
        assertFalse("for Thrillers in genre, isKidFriendlyEligible should return false", isKidFriendlyEligible);
    }
}