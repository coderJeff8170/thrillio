package com.cognizant.thrillio.constants;

/**
 * @author cognizant
 */
public enum MovieGenre {

    CLASSICS("Classics"),
    DRAMA("Drama"),
    SCIFI_AND_FANTASY("Sci-Fi & Fantasy"),
    COMEDY("Comedy"),
    CHILDREN_AND_FAMILY("Children & Family"),
    ACTION_AND_ADVENTURE("Action & Adventure"),
    THRILLERS("Thrillers"),
    MUSIC_AND_MUSICALS("Music & Musicals"),
    TELEVISION("Television"),
    HORROR("Horror"),
    SPECIAL_INTEREST("Special Interest"),
    INDEPENDENT("Independent"),
    SPORTS_AND_FITNESS("Sports & Fitness"),
    ANIME_AND_ANIMATION("Anime & Animation"),
    GAY_AND_LESBIAN("Gay & Lesbian"),
    CLASSIC_MOVIE_MUSICALS("Classic Movie Musicals"),
    FAITH_AND_SPIRITUALITY("Faith & Spirituality"),
    FOREIGN_DRAMAS("Foreign Dramas"),
    FOREIGN_THRILLERS("Foreign Thrillers"),
    TV_SHOWS("Tv Shows"),
    DRAMAS("Dramas"),
    ROMANTIC_MOVIES("Romantic Movies"),
    COMEDIES("Comedies"),
    DOCUMENTARIES("Documentaries"),
    FOREIGN_MOVIES("Foreign Movies");

    private MovieGenre(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
