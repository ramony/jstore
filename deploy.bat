@echo on
rem set JAVA_HOME=C:\Users\Ray\.jdks\openjdk-18.0.2.1
rem set MAVEN_HOME=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2.2\plugins\maven\lib\maven3
set deployTag=%time:~1,1%%time:~3,2%%time:~6,2%
call "%MAVEN_HOME%\bin\mvn" -DdeployTag=%deployTag% package
rem call kubectl set image deploy jstore  *=127.0.0.1:5000/jstore:1.0.0-%deployTag%
call kubectl set image deployment/jstore  jstore=127.0.0.1:5000/jstore:1.0.0-%deployTag%
echo jstore:1.0.0-%deployTag% deployed