package com.site2go;

import org.junit.Before;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationTest {
    protected Validator validator;

    @Before
    public void createValidator () {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
