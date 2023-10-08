# shortify
Shortify is an URI Shortening full stack application written in Vue and Quarkus

# Running in local mode

This mono repo was created in order to allow the execution of a simple docker compose local runtime environment for testing.

## Requirements

  * Docker

## Build and serving steps

  1. Clone the repository: ```git clone https://github.com/Henriqueapan/shortify.git```
  2. In the repo`s root directory run: ```docker compose -f docker-compose-dev.yml up --build -d```

The project has now been fully built and served in a docker compose environment.

## Accessing the local application

In a web browser, go to ```http://localhost:8080``` and feel free to use it as normal
