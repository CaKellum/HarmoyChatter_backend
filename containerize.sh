#!/bin/bash

# I didn't NULL check the $DICORD_RUNNING_USER or $DISCORD_RUNNING_PASSWORD 
# so make sure that this a set env variable to do this just run 
# $ export DICORD_RUNNING_USER=DB_USER_NAME
# $ export DISCORD_RUNNING_PASSWORD=DB_PASSWORD 
# 
# This will also build the docker-image with the current user's user name/discord-clone (eg.  user/discord-clone)
# mostly because if you are using this it likely means that you are choosing to build it on your own and might have
# modified this project thus I don't want it to be confused with the naming scheme of the releases and cause 
# confusing results
# 
# This also requires that you are able to access the maven cli tool

echo off
mvn clean package
tag="${USER}/discord-clone"

docker build --build-arg DB_USER="$DICORD_RUNNING_USER" DB_PASS="$DISCORD_RUNNING_PASSWORD" -t $tag .
# This will make the container run, specifying open ports at 8080 which is the default for this project
docker run --name discord-clone -itd -p 8080:8080/tcp $tag 