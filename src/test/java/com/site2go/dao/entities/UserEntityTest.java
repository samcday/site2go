package com.site2go.dao.entities;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserEntityTest {
    @Test
    public void testEquality() {
        UserEntity entity = new UserEntity() {{
            this.setEmail("test@user.com");
        }};

        assertFalse(entity.equals(null));
        assertTrue(entity.equals(entity));
        assertTrue(entity.equals(new UserEntity() {{
            this.setEmail("test@user.com");
        }}));
        assertFalse(entity.equals(new UserEntity() {{
            this.setEmail("fake@foo.com");
        }}));
    }
}
