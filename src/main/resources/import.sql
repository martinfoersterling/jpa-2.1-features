
-- create function for stored procedure example
CREATE FUNCTION get_magic_number(magic INT) RETURNS INT RETURN 13 * magic

-- create function for named stored procedure example
CREATE FUNCTION get_countries() RETURNS TABLE (id bigint, locale varchar(255), name varchar(255), time_zone varchar(255)) READS SQL DATA RETURN TABLE(SELECT * FROM COUNTRY)