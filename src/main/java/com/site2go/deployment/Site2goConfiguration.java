package com.site2go.deployment;

import com.yammer.dropwizard.config.Configuration;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Site2goConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String profile = "prod";

    public String getProfile() {
        return profile;
    }
}
