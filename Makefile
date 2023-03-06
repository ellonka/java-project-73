setup:
	./gradlew wrapper --gradle-version 7.4

clean:
	./gradlew clean

build:
	./gradlew clean build

start:
	./gradlew bootRun --args='--spring_profiles_active=dev'

start-prod:
	./gradlew bootRun --args='--spring_profiles_active=prod'

install:
	./gradlew installDist

start-dist:
	./build/install/app/bin/app

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

check-updates:
	./gradlew dependencyUpdates

generate-migrations:
	./gradlew diffChangeLog

.PHONY: build