# Stage 1: Build the application using Maven and the JDK
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy only the pom.xml first, to leverage Docker's layer caching
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Give execution permission to the Maven wrapper (needed on Linux)
RUN chmod +x mvnw

# Download dependencies (this layer is cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code and build the jar
COPY src src
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application using a lightweight JRE (no need for the full JDK)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy only the built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]