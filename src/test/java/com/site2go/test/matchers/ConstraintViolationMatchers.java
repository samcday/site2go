package com.site2go.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import javax.validation.ConstraintViolation;

public class ConstraintViolationMatchers {
    public static final ConstraintViolationMessageMatcher violationWithMessage(String expectedMessage) {
        return new ConstraintViolationMessageMatcher(expectedMessage);
    }

    public static class ConstraintViolationMessageMatcher<T> extends TypeSafeMatcher<ConstraintViolation<T>> {
        private String expectedMessage;

        public ConstraintViolationMessageMatcher(String expectedMessage) {
            this.expectedMessage = expectedMessage;
        }

        @Override
        public boolean matchesSafely(ConstraintViolation<T> constraintViolation) {
            return constraintViolation.getMessage().equals(this.expectedMessage);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("error with message" + this.expectedMessage);
        }
    }
}
