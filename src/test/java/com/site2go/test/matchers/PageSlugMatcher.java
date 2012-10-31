package com.site2go.test.matchers;

import com.site2go.dto.Page;
import com.site2go.services.impl.PageServiceImplTest;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class PageSlugMatcher extends TypeSafeMatcher<Page> {
    private String expectedSlug;

    public PageSlugMatcher(String expectedSlug) {
        this.expectedSlug = expectedSlug;
    }

    @Override
    public boolean matchesSafely(Page page) {
        if(page == null) return false;
        return page.getSlug().equals(this.expectedSlug);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("page to have slug " + this.expectedSlug);
    }
}
