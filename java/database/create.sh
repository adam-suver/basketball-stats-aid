export PGPASSWORD='ZFBd0MXh5r6ZKfe0hKZE'
DATABASE=railway
BASEDIR=$(dirname $0)

psql -h containers-us-west-58.railway.app -U postgres -p 5658 -d railway -f "$BASEDIR/dropdb.sql" &&
psql -h containers-us-west-58.railway.app -U postgres -p 5658 -d railway -f "$BASEDIR/schema.sql" &&
psql -h containers-us-west-58.railway.app -U postgres -p 5658 -d railway -f "$BASEDIR/data.sql" &&
psql -h containers-us-west-58.railway.app -U postgres -p 5658 -d railway -f "$BASEDIR/user.sql"