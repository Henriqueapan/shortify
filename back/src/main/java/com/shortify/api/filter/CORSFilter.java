package com.shortify.api.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@Provider
public class CORSFilter implements ContainerResponseFilter {
    @ConfigProperty(name = "quarkus.http.cors.origins", defaultValue = "*")
    String allowedOrigins;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        List<Object> matchedResources = requestContext.getUriInfo().getMatchedResources();
        if (matchedResources
                .stream()
                .allMatch((matchedResource) ->
                        "URIRecordResource".equals(matchedResource.getClass().getSuperclass().getSimpleName())
                )
        ) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", allowedOrigins);
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, HEAD");
        } else {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
        }
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        responseContext.getHeaders().add("Access-Control-Expose-Headers", "Location");
    }
}
