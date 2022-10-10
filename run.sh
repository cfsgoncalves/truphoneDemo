#!/env/bash

docker-compose up --build -d db db-test
mvn clean install
docker-compose up --build -d app
