# Usar una imagen base con Java
FROM amazoncorretto:21-alpine-jdk

# Copiar el JAR de la aplicación
COPY target/MendeleevAPI-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
