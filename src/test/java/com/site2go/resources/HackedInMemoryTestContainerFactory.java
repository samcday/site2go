/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2010-2011 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.site2go.resources;

import com.site2go.annotations.ServerProviderOnly;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.WebApplication;
import com.sun.jersey.spi.container.WebApplicationFactory;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.impl.container.inmemory.TestResourceClientHandler;
import com.sun.jersey.test.framework.spi.container.TestContainer;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

// TODO: figure out why square peg isn't fitting in round hole.
// Seriously though, we had to copy and hack on this factory to ensure we could get providers working correctly.
// Specifics in getClient().
/**
 * A low-level test container factory for creating test container instances
 * when testing in memory without using underlying HTTP client and server side
 * functionality to send requests and receive responses.
 *
 * @author Srinivas.Bhimisetty@Sun.COM
 */
public class HackedInMemoryTestContainerFactory implements TestContainerFactory {

    public Class<LowLevelAppDescriptor> supports() {
        return LowLevelAppDescriptor.class;
    }

    public TestContainer create(URI baseUri, AppDescriptor ad) {
        if (!(ad instanceof LowLevelAppDescriptor))
            throw new IllegalArgumentException(
                    "The application descriptor must be an instance of LowLevelAppDescriptor");

        return new InMemoryTestContainer(baseUri, (LowLevelAppDescriptor)ad);
    }

    /**
     * The class defines methods for starting/stopping an in-memory test container,
     * and for running tests on the container.
     */
    private static class InMemoryTestContainer implements TestContainer {

        private static final Logger LOGGER =
                Logger.getLogger(InMemoryTestContainer.class.getName());

        final URI baseUri;

        final ResourceConfig resourceConfig;

        final WebApplication webApp;

        /**
         * Creates an instance of {@link InMemoryTestContainer}
         * @param baseUri URI of the application
         * @param ad instance of {@link com.sun.jersey.test.framework.LowLevelAppDescriptor}
         */
        private InMemoryTestContainer(URI baseUri, LowLevelAppDescriptor ad) {
            this.baseUri = UriBuilder.fromUri(baseUri).build();

            LOGGER.info("Creating low level InMemory test container configured at the base URI "
                    + this.baseUri);

            this.resourceConfig = ad.getResourceConfig();

            addLoggingFilter(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, resourceConfig);
            addLoggingFilter(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, resourceConfig);

            this.webApp = initiateWebApplication(resourceConfig);
        }

        @SuppressWarnings("unchecked")
        private void addLoggingFilter(String property, ResourceConfig rc) {
            final Object requestFilters = rc.getProperties().get(property);
            if(requestFilters == null) {
                rc.getProperties()
                        .put(property, LoggingFilter.class.getName());
            } else if(requestFilters instanceof List) {
                List requestFilterList = (List)requestFilters;
                requestFilterList.add(LoggingFilter.class.getName());
                rc.getProperties()
                        .put(property, requestFilterList);
            } else {
                List requestFilterList = new ArrayList();
                requestFilterList.add(requestFilters);
                requestFilterList.add(LoggingFilter.class.getName());
                rc.getProperties()
                        .put(property, requestFilterList);
            }
        }

        // We made changes here.
        // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        public Client getClient() {
            ClientConfig clientConfig = null;
            Set<Object> providerSingletons = resourceConfig.getProviderSingletons();

            if (providerSingletons.size() > 0) {
                clientConfig = new DefaultClientConfig();
                for(Object providerSingleton : providerSingletons) {
                    // ... And here's the massive change. So basically it seems if you register singleton providers, the
                    // default InMemoryTestContainerFactory assumes they should be fair game to be registered on the
                    // Client too. Not so. What if we have a singleton Provider that wants to access HttpContext? As far
                    // as I'm aware, that's a server side only thing. So what we do here is check if the Provider has
                    // been annotated with @ServerProviderOnly - if it has, we don't register it in clientConfig.
                    if(providerSingleton.getClass().getAnnotation(ServerProviderOnly.class) != null) continue;
                    clientConfig.getSingletons().add(providerSingleton);
                }
            }

            if(clientConfig == null) {
                clientConfig = new DefaultClientConfig();
            }

            Client client = (clientConfig == null) ?
                    new Client(new TestResourceClientHandler(baseUri, webApp)) :
                    new Client(new TestResourceClientHandler(baseUri, webApp), clientConfig);

            return client;
        }

        public URI getBaseUri() {
            return baseUri;
        }

        public void start() {
            if (!webApp.isInitiated()) {
                LOGGER.info("Starting low level InMemory test container");

                webApp.initiate(resourceConfig);
            }
        }

        public void stop() {
            if (webApp.isInitiated()) {
                LOGGER.info("Stopping low level InMemory test container");

                webApp.destroy();
            }
        }

        private WebApplication initiateWebApplication(ResourceConfig rc) {
            WebApplication webapp = WebApplicationFactory.createWebApplication();
            return webapp;
        }

    }

}