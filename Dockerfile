FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Ambiente de Execução leve (JRE)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Comando para iniciar o Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]