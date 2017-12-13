package com.bits.ingest.spacetracker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class RequestMetadata {
    
    @JsonProperty("Total")
    private long total;
    @JsonProperty("Limit")
    private long limit;
    @JsonProperty("LimitOffset")
    private long limitOffset;
    @JsonProperty("ReturnedRows")
    private long returnedRows;
    @JsonProperty("RequestTime")
    private String requestTime;
    @JsonProperty("DataSize")
    private String dataSize;
    
    public RequestMetadata() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getLimitOffset() {
        return limitOffset;
    }

    public void setLimitOffset(long limitOffset) {
        this.limitOffset = limitOffset;
    }

    public long getReturnedRows() {
        return returnedRows;
    }

    public void setReturnedRows(long returnedRows) {
        this.returnedRows = returnedRows;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    @Override
    public String toString() {
        return "request_metadata:{" + "Total:" + total + ", Limit:" + limit + ", LimitOffset:" + limitOffset 
                + ", ReturnedRows:" + returnedRows + ", RequestTime:'" + requestTime + "', DataSize:'" + dataSize + "'}";
    }    
}
