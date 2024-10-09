# Verwende ein offizielles OpenJDK-Image als Basis
FROM openjdk:17-jdk-slim

# Setze das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiere die kompilierte JAR-Datei in den Container
COPY target/ticketsystem-0.0.1-SNAPSHOT.jar app.jar

# Mache Port 9090 verfügbar
EXPOSE 9090

# Starte die JAR-Datei
ENTRYPOINT ["java", "-jar", "app.jar"]