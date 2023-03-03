setup:
	./gradlew wrapper --gradle-version 7.4

clean:
	./gradlew clean

build:
	./gradlew clean build

start:
	./gradlew bootRun --args='--spring.profiles.active=dev'

start-prod:
	./gradlew bootRun --args='--spring.profiles.active=prod'

install:
	./gradlew installDist

start-dist:
	./build/install/app/bin/app

lint:
	./gradlew checkstyleMain checkstyleTest

generate-migrations:
	gradle diffChangeLog

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

check-updates:
	./gradlew dependencyUpdates

generate-migrations:
	./gradlew diffChangeLog

.PHONY: build