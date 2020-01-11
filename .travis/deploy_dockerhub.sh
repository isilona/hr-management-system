docker login --username $DOCKER_USER --password $DOCKER_PASS
docker build ./ -t $HEROKU_APP_NAME
docker push $DOCKER_REPO