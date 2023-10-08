package com.shortify.api.resource;

import com.shortify.api.dto.URIRecordDTO;
import com.shortify.api.entity.URIRecordEntity;
import com.shortify.api.responseBody.SuccessResponseBody;
import com.shortify.api.service.URIRecordService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/shortify/api/v1/uri-record")
public class URIRecordResource {
    URIRecordService uriRecordService = new URIRecordService();

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createUriRecord(@Valid URIRecordDTO uriRecordDTO) {
        String uriHash = uriRecordService.createUriResource(uriRecordDTO.getUri());

        return Response.created(URI.create(uriHash)).build();
    }

    @GET
    @Path("/{uriHash}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findURIRecord(@PathParam("uriHash") String uriHash) {
        URIRecordEntity uriRecordEntity = uriRecordService.findURIRecordByHash(uriHash);

        return uriRecordEntity == null ?
                Response.status(Response.Status.NOT_FOUND).build() :
                Response.status(Response.Status.OK)
                        .entity(new SuccessResponseBody(Response.Status.OK, "Shortified URI Record found", uriRecordEntity.toHashMap()))
                        .build();
    }
}
