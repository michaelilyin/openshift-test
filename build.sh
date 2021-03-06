#!/usr/bin/env bash

docker login -e $DOCKER_EMAIL -u $DOCKER_USER -p $DOCKER_PASS

#TAG
export TAG=`if [ "$TRAVIS_BRANCH" == "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ]; then echo "latest"; else echo $TRAVIS_BRANCH ; fi`

#SOURCE SERVICE
export SOURCE_SERVICE=michaelilyin/openshift-test-source-service
docker build -t $SOURCE_SERVICE:$TAG ./source-service
docker tag $SOURCE_SERVICE:$COMMIT $SOURCE_SERVICE:$TAG
docker push $SOURCE_SERVICE

#API SERVICE
export API_SERVICE=michaelilyin/openshift-test-api-service
docker build -t $API_SERVICE:$TAG ./api-service
docker tag $API_SERVICE:$COMMIT $API_SERVICE:$TAG
docker push $API_SERVICE
