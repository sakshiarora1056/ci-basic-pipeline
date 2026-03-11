.PHONY: build docker test all

build:
	mvn clean package

docker:
	docker build -t demo-app:latest docker/

test:
	mvn test

all: build test docker

