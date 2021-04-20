package com.alinatkachuk.socialnetwork.security;

public enum ApplicationPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_ON("user:on"),
    USER_OFF("user:off"),
    STATISTICS_READ("statistics:read"),
    FEED_READ("feed:read"),
    MESSAGE_WRITE("message:write"),
    POST_WRITE("post:write"),
    COMMENT_WRITE("comment:write"),
    LIKE_ON("like:on"),
    SUBSCRIPTION_ON("subscription:on"),
    SUBSCRIPTION_OFF("subscription:off");

    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
