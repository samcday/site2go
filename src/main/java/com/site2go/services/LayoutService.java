package com.site2go.services;

import com.site2go.dto.Layout;
import com.site2go.dto.Site;

public interface LayoutService {
    public Layout getBySlug(Site site, String slug);
}
