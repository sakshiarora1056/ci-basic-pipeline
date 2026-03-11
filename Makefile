.PHONY: build docker test all

build:
	mvn clean package

docker:
	docker build -f docker/Dockerfile -t demo-app:latest .

test:
	mvn test

all: build test docker

