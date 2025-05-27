FROM tomcat:9.0
LABEL maintainer="GirishPote"

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR to webapps
COPY DummyMVC.war /usr/local/tomcat/webapps/

EXPOSE 8081
