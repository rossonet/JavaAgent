#!/bin/bash
echo "Gradle buildDocker"
./gradlew buildDocker
echo "per il test locale: #docker run -p 8080:8080 -t rossonet/agentear4k:0.0.2-SNAPSHOT"
echo "per autenticarsi: #docker login --username=rossonet"
echo "per pubblicare su DockerHub: #docker push rossonet/agentear4k:0.0.2-SNAPSHOT"

exit 0
