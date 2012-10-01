insert into site(name, domain, modified_date, created_date) values('Test Site', 'test.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into page(slug, site_id, title, meta_title, meta_description, meta_keywords, modified_date, created_date) values('testpage', 1, 'Title', 'Meta Title', 'Meta Description', 'Meta Keywords', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);