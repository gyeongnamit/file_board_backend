FROM gradle:8.11.1-jdk17 AS build

WORKDIR /app
COPY build.gradle settings.gradle gradlew /app/  

COPY gradle /app/gradle

COPY src /app/src

#COPY entrypoint.sh /entrypoint.sh

# 실행 권한 부여
RUN chmod +x ./gradlew

RUN ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/build/libs/ /app/

COPY --from=build /app/build/libs/*SNAPSHOT.war /app/app.war

#COPY entrypoint.sh /app/entrypoint.sh
#RUN chmod +x /app/entrypoint.sh


# /app/entrypoint.sh 실행
#ENTRYPOINT ["/app/entrypoint.sh"]

# .war 파일 실행 명령 설정
ENTRYPOINT ["java", "-jar", "/app/app.war"]