package com.shortify.api.responseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@RegisterForReflection
public class SuccessResponseBody {
    private Response.Status status;

    private String message;

    private HashMap<String, ?> content;

    public SuccessResponseBody(Response.Status status) {
        this.status = status;
        this.message = "Successful request";
    }

    public SuccessResponseBody(Response.Status status, HashMap<String, ?> content) {
        this.status = status;
        this.message = "Successful request";
        this.content = content;
    }
}
