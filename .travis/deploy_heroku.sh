wget -qO- https://toolbelt.heroku.com/install-ubuntu.sh | sh
heroku plugins:install @heroku-cli/plugin-container-registry
docker login --username=$DOCKER_USER --password=$HEROKU_API_KEY registry.heroku.com
heroku container:push -a $HEROKU_APP_NAME web
heroku container:release -a $HEROKU_APP_NAME web
