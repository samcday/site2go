package com.site2go;

import com.site2go.dto.Site;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static com.site2go.test.matchers.ConstraintViolationMatchers.violationWithMessage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class SiteValidationTest extends ValidationTest {
    private Site site;

    @Before
    public void setup() {
        this.site = new Site();
        this.site.setName("Test Site");
        this.site.setDomain("test.com");
    }

    @Test
    public void testValidateNullDomain() {
        this.site.setDomain(null);
        Set<ConstraintViolation<Site>> errors = this.validator.validate (this.site);
        assertThat(errors.size(), is(1));
        assertThat(errors, hasItem(violationWithMessage("may not be null")));
    }
}
