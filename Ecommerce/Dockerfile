FROM openjdk:17
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} Ecommerce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Ecommerce-0.0.1-SNAPSHOT.jar"]