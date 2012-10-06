package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.PageEntity;
import com.site2go.dao.repositories.PageRepository;
import com.site2go.util.DevDataBootstrap;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PageRepositoryIT extends RepositoryITBase {
    @Autowired private PageRepository pageRepository;
    @Autowired private DevDataBootstrap devDataBootstrap;

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

    @Test
    public void testListBySite() {
        Set<PageEntity> pageEntities = this.pageRepository.listBySite(1);
        assertThat(pageEntities, is(notNullValue()));
        assertThat(pageEntities.size(), is(1));
        assertThat(pageEntities.iterator().next(), is(equalTo(this.devDataBootstrap.site1_page1)));
    }

    @Test
    public void testListByNonexistentSite() {
        Set<PageEntity> pageEntities = this.pageRepository.listBySite(666);
        assertThat(pageEntities, is(notNullValue()));
        assertThat(pageEntities.size(), is(0));
    }
}
