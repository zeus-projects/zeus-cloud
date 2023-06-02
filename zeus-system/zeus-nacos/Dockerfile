FROM openjdk:8-jdk-alpine

RUN mkdir -p /zeus-nacos/logs

WORKDIR /zeus-nacos

COPY target/zeus-nacos.jar zeus-nacos.jar

EXPOSE 8848

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Dserver.tomcat.basedir=file:/zeus-nacos/logs"

CMD sleep 30; java -jar $JAVA_OPTS zeus-nacos.jar