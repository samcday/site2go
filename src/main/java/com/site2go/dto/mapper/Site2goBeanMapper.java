package com.site2go.dto.mapper;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dto.Site;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;

public class Site2goBeanMapper extends DozerBeanMapper {
    public Site2goBeanMapper() {
        super();
        this.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(SiteEntity.class, Site.class)
                    .fields("defaultLayout.id", "defaultLayout")
                    .fields("modifiedDate", "modifiedDate", copyByReference())
                    .fields("createdDate", "createdDate", copyByReference());
            }
        });
    }
}
