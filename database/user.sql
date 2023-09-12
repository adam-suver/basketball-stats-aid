-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER stats_owner
WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO stats_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO stats_owner;

CREATE USER stats_appuser
WITH PASSWORD 'finalcapstone';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO stats_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO stats_appuser;
