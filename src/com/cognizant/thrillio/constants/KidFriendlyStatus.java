package com.cognizant.thrillio.constants;

import org.omg.CORBA.UNKNOWN;

public enum KidFriendlyStatus {

    APPROVED("approved"),
    REJECTED("rejected"),
    UNKNOWN("unknown");

    private String name;

    private KidFriendlyStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
