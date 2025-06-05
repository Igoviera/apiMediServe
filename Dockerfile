FROM ubuntu:latest AS build

# Atualiza e instala openjdk e maven em um único RUN e limpa cache para imagem menor
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copia só os arquivos necessários para build (exemplo: pom.xml e src)
COPY pom.xml .
COPY src ./src

# Build do projeto
RUN mvn clean install -DskipTests

# Imagem de runtime menor
FROM openjdk:17-jdk-slim

EXPOSE 8080

# Copia o jar gerado da etapa de build (verifique o caminho correto do jar)
COPY --from=build /target/apiMediServe-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
