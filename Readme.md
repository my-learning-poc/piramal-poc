# Piramal POC
## Development infrastructure
### Set enviornment variables
```shell
export DATAFLOW_VERSION=2.10.2
export SKIPPER_VERSION=2.9.2
export HOST_MOUNT_PATH=/Users/rajan/IdeaProjects/piramal-poc/database/sdcf
export BP_JVM_VERSION=-jdk17
export HOST_MOUNT_PATH=~/.m2
export DOCKER_MOUNT_PATH=/home/cnb/.m2
```
### Start docker containers
```shell
docker compose up -d
```
### Stop docker containers
```shell
docker compose down
rm -rf /Users/rajan/IdeaProjects/piramal-poc/database/postgres-db/*
ls -all /Users/rajan/IdeaProjects/piramal-poc/database/postgres-db/
```
simple-job-app
maven://com.piramal.lms:poc:0.0.1-SNAPSHOT