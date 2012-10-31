package com.site2go.test.matchers;

import com.site2go.dto.Site;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class SiteDomainMatcher extends TypeSafeMatcher<Site> {
    private String domain;

    public SiteDomainMatcher(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean matchesSafely(Site site) {
        return site.getDomain().equals(this.domain);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("site with domain " + this.domain);
    }
}
