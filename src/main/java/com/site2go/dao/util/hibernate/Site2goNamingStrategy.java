package com.site2go.dao.util.hibernate;

import org.hibernate.AssertionFailure;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.internal.util.StringHelper;

public class Site2goNamingStrategy extends ImprovedNamingStrategy {
    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        String header = propertyName != null ? StringHelper.unqualify(propertyName) : propertyTableName;
        if (header == null) throw new AssertionFailure("NamingStrategy not properly filled");
        return columnName(header) + "_" + referencedColumnName;
    }
}
