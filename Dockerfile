FROM maven:3.5-alpine

RUN mkdir /app

COPY pom.xml /app/.
ADD ./ /app/
WORKDIR /app

# questo anticipa il download delle dipendenze ed evita il timeout su bitbucket
RUN mvn dependency:go-offline

RUN mvn clean package -Duser.timezone=UTC

#fix javamelody font problem in alpine
RUN apk --update add fontconfig ttf-dejavu

EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar /app/target/palestra-in-cloud-api.jar -Duser.timezone=UTC" ]
