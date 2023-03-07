FROM gradle:7.4.2-jdk17

WORKDIR /app

COPY ./ .

RUN gradle installDist

CMD ./gradlew bootRun --args='--spring_profiles_active=prod'