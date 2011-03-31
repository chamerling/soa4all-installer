#!/bin/sh

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done
 
PRGDIR=`dirname "$PRG"`
EXECUTABLE=shutdown.sh

# Check that target executable exists
if [ ! -x "$PRGDIR"/apache-tomcat/bin/"$EXECUTABLE" ]; then
  echo "Cannot find $PRGDIR/apache-tomcat/bin/$EXECUTABLE"
  echo "This file is needed to run this program"
  exit 1
fi

echo "Stopping the SOA4ALl platform..."

exec "$PRGDIR"/apache-tomcat/bin/"$EXECUTABLE" "$@"
