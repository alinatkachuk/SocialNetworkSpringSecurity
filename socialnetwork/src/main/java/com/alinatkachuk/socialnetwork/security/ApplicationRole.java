package com.alinatkachuk.socialnetwork.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.alinatkachuk.socialnetwork.security.ApplicationPermission.*;

public enum ApplicationRole {
    USER(Sets.newHashSet(USER_READ, USER_WRITE, USER_ON, USER_OFF, FEED_READ, MESSAGE_WRITE, POST_WRITE, COMMENT_WRITE,
            LIKE_ON, SUBSCRIPTION_ON, SUBSCRIPTION_OFF)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, USER_ON, USER_OFF, STATISTICS_READ, FEED_READ, MESSAGE_WRITE,
            POST_WRITE, COMMENT_WRITE, LIKE_ON, SUBSCRIPTION_ON, SUBSCRIPTION_OFF));

    private final Set<ApplicationPermission> permissions;

    ApplicationRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return permissions;
    }

    public Set <SimpleGrantedAuthority> getGrantedAuthorities() {
        Set <SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
