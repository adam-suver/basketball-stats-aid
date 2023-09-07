export PGPASSWORD='dIOWrqhOP41Y3J3dkqcz'
DATABASE=railway

psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway -f "$BASEDIR/dropdb.sql" &&
psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway -f "$BASEDIR/schema.sql" &&
psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway -f "$BASEDIR/data.sql" &&
psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway -f "$BASEDIR/user.sql"