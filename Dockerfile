FROM openjdk:17
VOLUME /files
COPY build/libs/YaspTestEx-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 8080