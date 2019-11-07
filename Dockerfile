FROM java:8
EXPOSE 8080
ADD /target/api-campanha-0.0.1-SNAPSHOT.jar api-campanha-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","api-campanha-0.0.1-SNAPSHOT.jar"]