# rem set JAVA_HOME=C:\Users\Ray\.jdks\openjdk-18.0.2.1
MAVEN_HOME="/Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/bin"
deployTag=$RANDOM
"$MAVEN_HOME/mvn" -DdeployTag=$deployTag package
kubectl set image deployment/jstore  jstore=127.0.0.1:5000/jstore:1.0.0-$deployTag
echo jstore:1.0.0-$deployTag deployed