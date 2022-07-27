package com.softserve.edu.sporthubujp.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.softserve.edu.sporthubujp.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    UNAUTHORIZED_USER(Sets.newHashSet(

            VIEW_SPORTS_NEWS,
            SUBSCRIBE_TO_RECEIVE_SPORTS_NEWS,
            SHARE_THE_NEWS_VIA_THE_SOCIAL_MEDIA,
            LOGIN_TO_THE_SPORTS_HUB_SITE,
            SIGN_UP_TO_THE_SPORTS_HUB_SITE
    )

    ),
    AUTHORIZED_USER(Sets.newHashSet(
            VIEW_SPORTS_NEWS,
            SUBSCRIBE_TO_RECEIVE_SPORTS_NEWS,
            SHARE_THE_NEWS_VIA_THE_SOCIAL_MEDIA,
            LOGIN_TO_THE_SPORTS_HUB_SITE,
            SIGN_UP_TO_THE_SPORTS_HUB_SITE,
            MANAGE_PERSONAL_INFO,
            MANAGE_TEAM_SUBSCRIPTIONS,
            MANAGE_SURVEYS,
            ANSWER_THE_SURVEYS,
            COMMENT_ON_AN_ARTICLE

    )

    ),
    ADMIN(Sets.newHashSet(
            CONFIGURE_SITE_FOOTER,
            CONFIGURE_SITE_MENU,
            CONFIGURE_SITE_USERS,
            CONFIGURE_STRUCTURE_OF_THE_PAGES,
            CONFIGURE_ARTICLES,
            CONFIGURE_SPORTS_CATEGORIES,
            CONFIGURE_SPORTS_SUBCATEGORIES,
            CONFIGURE_TEAMS,
            CONFIGURE_RELATIONS_BETWEEN_CATEGORIES_SUBCATEGORIES_TEAMS,
            CONFIGURE_SITE_LANGUAGES,
            CONFIGURE_SOCIAL_NETWORKS_SUPPORT,
            CONFIGURE_SURVEYS,
            CONFIGURE_BANNERS,
            CONFIGURE_ADVERTISEMENTS
    ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;

    }
}



