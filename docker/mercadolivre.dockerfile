FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/*jar
COPY ${JAR_FILE} mercadolivre.jar
ENTRYPOINT ["java", "-jar", "/mercadolivre.jar"]
