package com.site2go.services;

import com.site2go.dto.Page;
import com.site2go.dto.Site;

import java.util.List;

public interface PageService {
    public Page getPageBySlug(Site site, String slug);
    public List<Page> getPagesBySite(Site site);
}
