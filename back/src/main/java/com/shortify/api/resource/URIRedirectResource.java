package com.shortify.api.resource;

import com.shortify.api.service.URIRecordService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/")
public class URIRedirectResource {
    URIRecordService uriRecordService = new URIRecordService();

    @GET
    @Path("/{uriHash}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response redirectToOriginalUri(@PathParam("uriHash") String uriHash) {
        String originalUri = uriRecordService.getLongUriByHash(uriHash);

        return originalUri == null ?
                Response.status(Response.Status.NOT_FOUND).build() :
                Response.status(Response.Status.SEE_OTHER).location(URI.create(originalUri)).build();
    }
}
