package com.cognizant.thrillio.entities;

import com.cognizant.thrillio.managers.BookmarkManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class WebLinkTest {

    WebLink webLink;
    boolean isKidFriendlyEligible;

    @Test
    public void testIsKidFriendlyEligible_shouldReturnFalse_ifPornInURL() {
        // Test 1: porn in url --false
        webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse("For porn in URL, isKidFriendlyEligible must return false", isKidFriendlyEligible);
    }

    @Test
    public void isKidFriendlyEligible_shouldReturnFalse_ifPornInTitle() {
        // Test 2: porn in title --false
        webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse("For porn in TITLE, isKidFriendlyEligible must return false", isKidFriendlyEligible);
    }

    @Test
    public void isKidFriendlyEligible_shouldReturnFalse_ifAdultInHost() {
        // Test 3: adult in host --false
        webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse("For adult in HOST, isKidFriendlyEligible must return false", isKidFriendlyEligible);
    }

    @Test
    public void isKidFriendlyEligible_shouldReturnTrue_ifAdultInURL_butNotInHost() {
        // Test 4: adult in url, but not in host --true
        webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Porn, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertTrue("For adult in URL but not host, isKidFriendlyEligible must return true", isKidFriendlyEligible);
    }

    @Test
    public void isKidFriendlyEligible_shouldReturnTrue_ifAdultInTitle() {
        // Test 5: adult in title --true
        webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming adult, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertTrue("For adult in TITLE, isKidFriendlyEligible must return true", isKidFriendlyEligible);
    }
}