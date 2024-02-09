# shortify
Shortify is an URI Shortening full stack application written in Vue and Quarkus.

# Usage

1. First, you type in the URI that you want to shorten
![Shortify](/readme_assets/shortify_1.png "First, you type in the URI that you want to shorten")

2. Shortify will then return a shortened version of that URI
![Shortify](/readme_assets/shortify_2.png "Shortify will then return a shortened version of that URI")

3. You can then copy and paste the shortened version of the URI on your browser
![Shortify](/readme_assets/short_URI_1.png "You can then copy and paste the shortened version of the URI on your browser")

4. When your browser sends a request to the shortened URI, Shortify will redirect you to the original one
![Shortify](/readme_assets/short_URI_2.png "When your browser sends a request to the shortened URI, Shortify will redirect you to the original one")

## Short URI hostname

Shortify will use the hostname of the server it is beign served on to create the shortened version of URIs.

If the application is served in local mode, the hostname will be `localhost`.

# Running in local mode

This mono repo was created in order to allow the execution of a simple docker compose local runtime environment for testing.

## Requirements

  * Docker

## Build and serving steps

  1. Clone the repository: ```git clone https://github.com/Henriqueapan/shortify.git```
  2. In the repo`s root directory run: ```docker compose -f docker-compose-dev.yml up --build -d```

The project has now been fully built and served in a docker compose environment.

## Accessing the local application

In a web browser, go to ```http://localhost:8080``` and feel free to use it as normal.

You can access the shortified version of the requested URI by copying it (clicking on the red box that contains the short URI) and pasting it on your browser.
