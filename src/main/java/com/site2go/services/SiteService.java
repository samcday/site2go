package com.site2go.services;

import com.site2go.dto.Site;
import com.site2go.dto.User;

import java.util.List;

public interface SiteService {
    public Site getSiteByDomain(String domain);
    public List<Site> getSitesByUser(User user);
}
