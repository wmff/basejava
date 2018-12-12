INSERT INTO resume (uuid, full_name) VALUES ('6c9e8ef7-5abd-4f4c-84cb-44cdc02d4003', 'name1');
INSERT INTO resume (uuid, full_name) VALUES ('9930ffa3-b1bb-4846-a69a-b90720d36bba', 'name1');
INSERT INTO resume (uuid, full_name) VALUES ('aaf4025d-4a48-49de-8214-edbceb503467', 'name1');

INSERT INTO contact (resume_uuid, type, value) VALUES ('6c9e8ef7-5abd-4f4c-84cb-44cdc02d4003','PHONE','phone1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('6c9e8ef7-5abd-4f4c-84cb-44cdc02d4003','SKYPE','skype1');

-- select * from resume
-- SELECT *, count(*) over() total_rows FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid='6c9e8ef7-5abd-4f4c-84cb-44cdc02d4003';
-- SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid ORDER BY full_name,uuid;
SELECT *  FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid ORDER BY full_name, uuid;

INSERT INTO contact (resume_uuid, type, value) VALUES ('6c9e8ef7-5abd-4f4c-84cb-44cdc02d4003','SKYPE','skype2');
UPDATE contact SET value = 'skype3' WHERE type = 'EMAIL' AND resume_uuid = '6c9e8ef7-5abd-4f4c-84cb-44cdc02d4003';
truncate table resume cascade