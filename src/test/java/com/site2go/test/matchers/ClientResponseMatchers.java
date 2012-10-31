package com.site2go.test.matchers;

import com.sun.jersey.api.client.ClientResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ClientResponseMatchers {
    public static final ClientResponseOkMatcher isOk() {
        return new ClientResponseOkMatcher();
    }

    public static class ClientResponseOkMatcher extends TypeSafeMatcher<ClientResponse> {
        @Override
        public boolean matchesSafely(ClientResponse clientResponse) {
            return clientResponse.getClientResponseStatus() == ClientResponse.Status.OK;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("status 200");
        }
    }
}
