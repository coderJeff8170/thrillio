package com.cognizant.thrillio.constants;


/**
 * @author cognizant
 */
public enum BookGenre {

    ART("Art"),
    BIOGRAPHY("Biography"),
    CHILDREN("Children"),
    FICTION("Fiction"),
    HISTORY("History"),
    MYSTERY("Mystery"),
    PHILOSOPHY("Philosophy"),
    RELIGION("Religion"),
    ROMANCE("Romance"),
    SELF_HELP("Self Help"),
    TECHNICAL("Technical");

    private BookGenre(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

}
