package com.bits.ingest.codex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class LoginToken {
    
    @JsonProperty("token")
    private String token;
    @JsonProperty("expires")
    private long expires;
    @JsonProperty("userUuid")
    private String userUuid;
    
    public LoginToken() {
        
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    @Override
    public String toString() {
        return "LoginToken{" + "token='" + token + "', expires=" + expires + ", userUuid='" + userUuid + "'}";
    }
}
