FROM java:8
#创作者名字
MAINTAINER raybyte
#容器的/tmp/目录挂载到本地var/lib/docker
VOLUME /tmp
#添加jar包并重命名，此处为jar，并不会解压
COPY jstore-1.0.0.jar /usr/local/jstore.jar

ENTRYPOINT ["java","-jar","/usr/local/jstore.jar"]