package com.shortify.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class URIRecordDTO {
    private String uri;

    @Override
    public String toString(){
        return "URIRecordDTO =>\n" +
                "{\n" +
                "    uri: " + this.uri + "\n" +
                "}\n";
    }
}
