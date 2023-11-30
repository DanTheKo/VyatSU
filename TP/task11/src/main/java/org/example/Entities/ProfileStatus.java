package org.example.Entities;

public enum ProfileStatus {
    OPEN("open"),
    FRIENDS_ONLY("friends only"),
    CLOSED("closed")
    ;
    private String type;
    ProfileStatus(String s) {
        type = s;
    }
    public String getType() {
        return type;
    }
}
