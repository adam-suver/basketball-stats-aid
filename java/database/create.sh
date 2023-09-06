#!/bin/bash
#export PGPASSWORD='postgres1'
#BASEDIR=$(dirname $0)
#DATABASE=basketball_stats
#psql -U postgres -f "$BASEDIR/dropdb.sql" &&
#createdb -U postgres $DATABASE &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/data.sql" &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"

#PGPASSWORD=dIOWrqhOP41Y3J3dkqcz psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway

export PGPASSWORD='dIOWrqhOP41Y3J3dkqcz'
BASEDIR=$(dirname $0)
DATABASE=railway

psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway -f "$BASEDIR/dropdb.sql" &&
psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway -f "$BASEDIR/schema.sql" &&
psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway -f "$BASEDIR/data.sql" &&
psql -h containers-us-west-150.railway.app -U postgres -p 7885 -d railway -f "$BASEDIR/user.sql"