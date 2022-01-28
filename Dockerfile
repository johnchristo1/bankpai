FROM openjdk:8
EXPOSE 8080
ADD target/moneytransfer-docker.jar moneytransfer-docker.jar 
ENTRYPOINT ["java","-jar","/moneytransfer-docker.jar"]