FROM amazoncorretto:11

ADD ./service/build/libs/service-1.0-SNAPSHOT.jar /app/service.jar
ADD ./ci/keystore.p12 /tls/keystore.p12

CMD ["java","-jar","/app/service.jar"]
