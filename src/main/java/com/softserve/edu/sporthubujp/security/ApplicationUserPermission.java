package com.softserve.edu.sporthubujp.security;

public enum ApplicationUserPermission {

    // UNAUTHORIZED
    VIEW_SPORTS_NEWS("view sports news"),
    SUBSCRIBE_TO_RECEIVE_SPORTS_NEWS("subscribe to recieve sports news"),
    SHARE_THE_NEWS_VIA_THE_SOCIAL_MEDIA("share the news via the social media"),
    LOGIN_TO_THE_SPORTS_HUB_SITE("login to the sports hub site"),
    SIGN_UP_TO_THE_SPORTS_HUB_SITE("sign up to the sports hub site"),

    // AUTHORIZED
    MANAGE_PERSONAL_INFO("manage personal info"),
    MANAGE_TEAM_SUBSCRIPTIONS("manage team subscriptions"),
    MANAGE_SURVEYS("manage surveys"),
    ANSWER_THE_SURVEYS("answer the surveys"),
    COMMENT_ON_AN_ARTICLE("comment on an article"),

    // ADMIN
    CONFIGURE_SITE_FOOTER("configure_site_footer"),
    CONFIGURE_SITE_MENU("configure site menu"),
    CONFIGURE_SITE_USERS("configure site users"),
    CONFIGURE_STRUCTURE_OF_THE_PAGES("configure structure of the pages"),
    CONFIGURE_ARTICLES("configure articles"),
    CONFIGURE_SPORTS_CATEGORIES("configure sports categories"),
    CONFIGURE_SPORTS_SUBCATEGORIES("configure sports subcategories"),
    CONFIGURE_TEAMS("configure teams"),
    CONFIGURE_RELATIONS_BETWEEN_CATEGORIES_SUBCATEGORIES_TEAMS("configure relations between categories, subcategories, teams"),
    CONFIGURE_SITE_LANGUAGES("configure site languages"),
    CONFIGURE_SOCIAL_NETWORKS_SUPPORT("configure social network support"),
    CONFIGURE_SURVEYS("configure surveys"),
    CONFIGURE_BANNERS("configure banners"),
    CONFIGURE_ADVERTISEMENTS("configure advertisements");
    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
