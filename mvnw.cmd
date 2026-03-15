@REM Maven Wrapper script for ShopNest (Windows)
@REM Downloads Maven if not available and runs the project

@echo off
setlocal

set MAVEN_VERSION=3.9.6
set MAVEN_HOME=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%
set MAVEN_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip

if not exist "%MAVEN_HOME%\apache-maven-%MAVEN_VERSION%\bin\mvn.cmd" (
    echo Downloading Maven %MAVEN_VERSION%...
    mkdir "%MAVEN_HOME%" 2>NUL
    set TMPFILE=%TEMP%\maven-%MAVEN_VERSION%.zip
    powershell -Command "Invoke-WebRequest -Uri '%MAVEN_URL%' -OutFile '%TEMP%\maven-%MAVEN_VERSION%.zip'"
    powershell -Command "Expand-Archive -Path '%TEMP%\maven-%MAVEN_VERSION%.zip' -DestinationPath '%MAVEN_HOME%' -Force"
    del "%TEMP%\maven-%MAVEN_VERSION%.zip" 2>NUL
)

set MVN_CMD=%MAVEN_HOME%\apache-maven-%MAVEN_VERSION%\bin\mvn.cmd
"%MVN_CMD%" %*
