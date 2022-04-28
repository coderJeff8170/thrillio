package com.cognizant.thrillio.constants;

/**
 * @author cognizant
 */
public enum UserType {

    USER("user"),
    EDITOR("editor"),
    CHIEF_EDITOR("chief editor");

    private UserType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
