FROM tomcat:9.0
LABEL maintainer="GirishPote"

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR to webapps
COPY target/DummyMVC.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
