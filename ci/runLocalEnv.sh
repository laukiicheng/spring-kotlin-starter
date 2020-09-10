#!/usr/bin/env bash

NC='\033[0m'
GREEN='\033[0;32m'

printf "${GREEN}Starting the containers spring kotlin starter depends on${NC}\n"

docker-compose -f ci/docker-compose.local.yml up -d --build &
wait
printf "${GREEN}All containers started${NC}\n"

exit
