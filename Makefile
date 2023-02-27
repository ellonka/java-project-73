clean:
	./gradlew clean

check-updates:
	./gradlew dependencyUpdates

install:
	./gradlew installDist

lint:
	./gradlew checkstyleMain

generate-migrations:
	gradle diffChangeLog

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

build:
	./gradlew clean build

.PHONY: build