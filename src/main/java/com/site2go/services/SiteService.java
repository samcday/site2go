package com.site2go.services;

import com.site2go.dto.Site;

public interface SiteService {
    public Site getSiteByDomain(String domain);
}
