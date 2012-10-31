package com.site2go.test.matchers;

public class SiteMatchers {
    public static SiteDomainMatcher siteWithDomain(String domain) {
        return new SiteDomainMatcher(domain);
    }
}
