package com.site2go.services;

import com.site2go.dto.Page;
import com.site2go.dto.Site;

public interface PageService {
    public Page getPageBySlug(Site site, String slug);
}
