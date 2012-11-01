package com.site2go.test.matchers;

import com.sun.jersey.api.client.ClientResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ClientResponseMatchers {
    public static final ClientResponseStatusMatcher isOk() {
        return new ClientResponseStatusMatcher(ClientResponse.Status.OK);
    }

    public static final ClientResponseStatusMatcher isNotFound() {
        return new ClientResponseStatusMatcher(ClientResponse.Status.NOT_FOUND);
    }

    public static class ClientResponseStatusMatcher extends TypeSafeMatcher<ClientResponse> {
        private ClientResponse.Status expectedStatus;

        public ClientResponseStatusMatcher(ClientResponse.Status expectedStatus) {
            this.expectedStatus = expectedStatus;
        }

        @Override
        public boolean matchesSafely(ClientResponse clientResponse) {
            return clientResponse.getClientResponseStatus() == this.expectedStatus;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("response status code " + this.expectedStatus.getStatusCode());
        }
    }
}
