#!/usr/bin/env bash

#OPENSHIFT_HOST=https://192.168.99.101:8443
#OPENSHIFT_PASSWORD=developer
#OPENSHIFT_USER=developer
#OPENSHIFT_PROJECT=test

function configure_service {
    oc get service $1
    if [ $? -ne 0 ]; then
        echo "Create service configuration for $1"
        oc create -n ${OPENSHIFT_PROJECT} -f ./openshift/$1-service.yml
    else
        echo "Update service configuration for $1"
        oc replace -n ${OPENSHIFT_PROJECT} -f ./openshift/$1-service.yml
    fi
}

function configure_deployment {
    oc get dc $1
    if [ $? -ne 0 ]; then
        echo "Create deployment configuration for $1"
        oc create -n ${OPENSHIFT_PROJECT} -f ./openshift/$1-deployment.yml
#        oc deploy $1 --cancel
    else
        echo "Update deployment configuration for $1"
        oc replace -n ${OPENSHIFT_PROJECT} -f ./openshift/$1-deployment.yml
    fi
}

function configure_route {
    oc get route $1
    if [ $? -ne 0 ]; then
        echo "Create route configuration for $1"
        oc create -n ${OPENSHIFT_PROJECT} -f ./openshift/$1-route.yml
    else
        echo "Update route configuration for $1"
        oc replace -n ${OPENSHIFT_PROJECT} -f ./openshift/$1-route.yml
    fi
}

function refresh_image {
    echo "Refresh image for $1 from $2"
    oc import-image $1-image --from $2 --confirm
}

#which ssh-agent || ( apt-get update -y && apt-get install openssh-client -y )
#eval $(ssh-agent -s)
#ssh-add <(echo "${SSH_PRIVATE_KEY}")
mkdir -p ~/.ssh
echo -e "Host *\n\tStrictHostKeyChecking no\n\n" > ~/.ssh/config

if [ -z "${OPENSHIFT_TOKEN}" ]; then
    (oc login ${OPENSHIFT_HOST} -u ${OPENSHIFT_USER} -p ${OPENSHIFT_PASSWORD} > /dev/null \
        && echo "login successful for ${OPENSHIFT_HOST}") || (echo "Can not login ${OPENSHIFT_HOST}"; exit 1)
else
    (oc login ${OPENSHIFT_HOST} --token ${OPENSHIFT_TOKEN} > /dev/null \
        && echo "login successful for ${OPENSHIFT_HOST}") || (echo "Can not login ${OPENSHIFT_HOST}"; exit 1)
fi

oc project ${OPENSHIFT_PROJECT} || (echo "Create new project ${OPENSHIFT_PROJECT}"; oc new-project ${OPENSHIFT_PROJECT})

echo "Deploy source service"
SOURCE_SERVICE="source-service"
configure_service ${SOURCE_SERVICE}
configure_deployment ${SOURCE_SERVICE}
refresh_image ${SOURCE_SERVICE} michaelilyin/openshift-test-source-service

echo "Deploy api service"
API_SERVICE="api-service"
configure_service ${API_SERVICE}
configure_deployment ${API_SERVICE}
configure_route ${API_SERVICE}
refresh_image ${API_SERVICE} michaelilyin/openshift-test-api-service
