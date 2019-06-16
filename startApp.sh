#!/bin/bash

docker-compose build

docker-compose up -d axonserver
docker-compose up