FROM openjdk:18-alpine
WORKDIR /app
COPY target/transaction-service-1.0.jar /app/transaction-service.jar
EXPOSE 8085
CMD ["java", "-jar", "transaction-service.jar"]
