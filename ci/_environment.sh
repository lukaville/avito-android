#!/usr/bin/env bash

if [[ -z "${DOCKER_REGISTRY+x}" ]]; then
    echo "ERROR: env DOCKER_REGISTRY is not specified"
    exit 1
fi

IMAGE_ANDROID_BUILDER=${DOCKER_REGISTRY}/android/builder:6c5b5e3d02
IMAGE_DOCKER_IN_DOCKER=${DOCKER_REGISTRY}/android/docker-in-docker-image:f7f8a4a4ba
