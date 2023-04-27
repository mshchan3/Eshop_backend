FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
COPY ./build/libs/Eshop-0.0.1-SNAPSHOT.jar FSSE2212_Backend.jar
ENTRYPOINT ["java","-jar","/FSSE2212_Backend.jar"]