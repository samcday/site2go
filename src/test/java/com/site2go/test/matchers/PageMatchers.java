package com.site2go.test.matchers;

public class PageMatchers {
    public static PageSlugMatcher pageWithSlug(String slug) {
        return new PageSlugMatcher(slug);
    }
}
