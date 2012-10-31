package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.repositories.LayoutRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class LayoutRepositoryIT extends RepositoryITBase {
    @Autowired private LayoutRepository layoutRepository;

    @Test
    public void testListBySite() {
        Set<LayoutEntity> layouts = this.layoutRepository.listBySite(1);
        assertThat(layouts.size(), is(1));
        LayoutEntity layout = layouts.iterator().next();
        assertThat(layout.getId(), is(1));
    }

    @Test
    public void testListByNonexistentSite() {
        Set<LayoutEntity> layouts = this.layoutRepository.listBySite(123);
        assertThat(layouts, notNullValue());
        assertThat(layouts.size(), is(0));
    }

    @Test
    public void testFindBySiteAndSlug() {
        LayoutEntity layout = this.layoutRepository.findBySiteAndSlug(1, "testlayout");
        assertThat(layout.getId(), is(1));
        assertThat(layout.getSite().getId(), is(1));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindByNonexistentSiteAndSlug() {
        LayoutEntity layout = this.layoutRepository.findBySiteAndSlug(123, "foo");
    }
}
