FROM maven:3.9.6-eclipse-temurin-21-alpine
WORKDIR /app
COPY pom.xml ./
COPY . .
RUN mvn verify -DskipTests=true -Dmaven.test.skip=true
EXPOSE 5017
CMD ["java","-jar","/app/target/gantour-0.0.1-SNAPSHOT.jar"]

