insert into site(name, domain, modified_date, created_date) values('Test Site', 'test.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into page(slug, site_id, modified_date, created_date) values('testpage', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);