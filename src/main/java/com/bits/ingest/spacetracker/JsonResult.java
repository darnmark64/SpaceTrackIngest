package com.bits.ingest.spacetracker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class JsonResult {
    
    @JsonProperty("request_metadata")
    private RequestMetadata requestMetadata;
    @JsonProperty("data")
    private List<Data> data = new ArrayList<>();
    
    public JsonResult() {
        
    }

    public RequestMetadata getRequestMetadata() {
        return requestMetadata;
    }

    public void setRequestMetadata(RequestMetadata requestMetadata) {
        this.requestMetadata = requestMetadata;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" + "requestMetadata=" + requestMetadata + ", data=" + data + "}";
    }
    
}
