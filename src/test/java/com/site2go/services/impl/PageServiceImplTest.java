package com.site2go.services.impl;

import com.google.common.collect.Sets;
import com.site2go.dao.entities.PageEntity;
import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.PageRepository;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.dto.Page;
import com.site2go.dto.Site;
import com.site2go.dto.mapper.Site2goBeanMapper;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.site2go.services.impl.PageServiceImplTest.PageSlugMatcher.pageWithSlug;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageServiceImplTest {
    private PageServiceImpl pageServiceImpl;
    private PageRepository mockPageRepository;
    private SiteRepository mockSiteRepository;

    @Before
    public void setup() {
        this.mockPageRepository = mock(PageRepository.class);
        this.mockSiteRepository = mock(SiteRepository.class);

        this.pageServiceImpl = new PageServiceImpl();
        this.pageServiceImpl.setPageRepository(this.mockPageRepository);
        this.pageServiceImpl.setSiteRepository(this.mockSiteRepository);
        this.pageServiceImpl.setBeanMapper(new Site2goBeanMapper());
    }

    @Test
    public void testGetPageBySlug() {
        when(this.mockSiteRepository.findByDomain("test.com")).thenReturn(new SiteEntity() {{
            this.setId(123);
            this.setDomain("test.com");
        }});
        when(this.mockPageRepository.findBySiteAndSlug(123, "testpage")).thenReturn(new PageEntity() {{
            this.setSlug("testpage");
        }});
        Page page = this.pageServiceImpl.getPageBySlug(new Site() {{ this.setDomain("test.com"); }}, "testpage");
        assertThat(page, is(notNullValue()));
        assertThat(page.getSlug(), is("testpage"));
    }

    @Test
    public void testGetNonexistentPageBySlug() {
        when(this.mockSiteRepository.findByDomain("test.com")).thenReturn(new SiteEntity() {{
            this.setId(123);
            this.setDomain("test.com");
        }});
        Page page = this.pageServiceImpl.getPageBySlug(new Site() {{ this.setDomain("test.com"); }}, "noexists");
        assertThat(page, is(nullValue()));
    }

    @Test
    public void testGetPageBySlugNonexistentSite() {
        Page page = this.pageServiceImpl.getPageBySlug(new Site() {{ this.setDomain("foo.com"); }}, "a page");
        assertThat(page, is(nullValue()));
    }

    @Test
    public void testGetPagesBySite() {
        when(this.mockSiteRepository.findByDomain("test.com")).thenReturn(new SiteEntity() {{
            this.setId(123);
        }});

        when(this.mockPageRepository.listBySite(123)).thenReturn(Sets.<PageEntity>newHashSet(
            new PageEntity() {{ this.setSlug("pageone"); }},
            new PageEntity() {{ this.setSlug("pagetwo"); }}
        ));

        List<Page> pages = this.pageServiceImpl.getPagesBySite(new Site() {{ this.setDomain("test.com"); }});
        assertThat(pages.size(), is(2));
        assertThat(pages, hasItem(pageWithSlug("pageone")));
        assertThat(pages, hasItem(pageWithSlug("pagetwo")));
    }

    public static class PageSlugMatcher extends TypeSafeMatcher<Page> {
        private String expectedSlug;

        protected PageSlugMatcher(String expectedSlug) {
            this.expectedSlug = expectedSlug;
        }

        @Override
        public boolean matchesSafely(Page page) {
            if(page == null) return false;
            return page.getSlug().equals(this.expectedSlug);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("page to have slug " + this.expectedSlug);
        }

        public static PageSlugMatcher pageWithSlug(String slug) {
            return new PageSlugMatcher(slug);
        }
    }
}
