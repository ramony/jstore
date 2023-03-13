FROM openjdk:8
#创作者名字
MAINTAINER raybyte

ARG JAR_FILE
#添加jar包并重命名，此处为jar，并不会解压
COPY ${JAR_FILE} /usr/local/jstore.jar

ENTRYPOINT ["java","-jar","/usr/local/jstore.jar", "--spring.profiles.active=cluster"]