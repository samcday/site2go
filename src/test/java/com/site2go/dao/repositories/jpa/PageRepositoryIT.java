package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.PageEntity;
import com.site2go.dao.repositories.PageRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PageRepositoryIT extends RepositoryITBase {
    @Autowired private PageRepository pageRepository;

    @Test
    public void testFindBySiteAndSlug() {
        PageEntity pageEntity = this.pageRepository.findBySiteAndSlug(1, "testpage");
        assertThat(pageEntity, is(notNullValue()));
        assertThat(pageEntity.getId(), is(1));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindBySiteAndNonexistentSlug() {
        this.pageRepository.findBySiteAndSlug(1, "notapage");
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindByNonexistentSiteAndSlug() {
        this.pageRepository.findBySiteAndSlug(666, "testpage");
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindByWrongSiteAndSlug() {
        this.pageRepository.findBySiteAndSlug(2, "testpage");
    }
}
