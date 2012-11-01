package com.site2go.test.matchers;

import com.site2go.dto.Layout;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class LayoutMatchers {
    public static final LayoutSlugMatcher layoutWithSlug(String slug) {
        return new LayoutSlugMatcher(slug);
    }

    public static class LayoutSlugMatcher extends TypeSafeMatcher<Layout> {
        private String expectedSlug;

        public LayoutSlugMatcher(String expectedSlug) {
            this.expectedSlug = expectedSlug;
        }

        @Override
        public boolean matchesSafely(Layout layout) {
            return layout.getSlug().equals(this.expectedSlug);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("layout with slug " + this.expectedSlug);
        }
    }
}
