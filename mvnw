#!/bin/sh
# Maven Wrapper script for ShopNest
# Downloads Maven if not available and runs the project

set -e

MAVEN_HOME="$HOME/.m2/wrapper/dists/apache-maven-3.9.6"
MAVEN_URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip"

if [ ! -d "$MAVEN_HOME" ]; then
    echo "Downloading Maven 3.9.6..."
    mkdir -p "$MAVEN_HOME"
    TMPFILE=$(mktemp)
    curl -sL "$MAVEN_URL" -o "$TMPFILE"
    unzip -qo "$TMPFILE" -d "$MAVEN_HOME"
    rm "$TMPFILE"
fi

MVN_CMD="$MAVEN_HOME/apache-maven-3.9.6/bin/mvn"
exec "$MVN_CMD" "$@"
