FROM maven as build
WORKDIR /opt/demo
COPY . /opt/demo
RUN mvn package

FROM tomcat
WORKDIR /usr/local/tomcat
COPY --from=build /opt/demo/target/*.war webapps
CMD catalina.sh run
