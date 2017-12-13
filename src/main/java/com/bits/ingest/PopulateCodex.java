package com.bits.ingest;

import com.bits.ingest.codex.Credentials;
import com.bits.ingest.codex.LoginToken;
import com.bits.ingest.codex.SpaceVehicleAsset;
import com.bits.ingest.spacetracker.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PopulateCodex {

    private static final Logger log = LoggerFactory.getLogger(PopulateCodex.class);

    //Login credentials
    @Value("${codex.username}")
    private String cUsername;

    @Value("${codex.password}")
    private String cPassword;

    //REST endpoints
    private final String loginUrl = "http://localhost:8080/codex/api/login";
    private final String svUrl = "http://localhost:8080/codex/api/space-vehicles";

    public long processData(List<Data> sptData) throws Exception {
        log.info("Data list contains " + sptData.size() + " elements; converting to CODEX SpaceVehicleAsset");
        List<SpaceVehicleAsset> svAssets = new ArrayList<>();
        for (Data d : sptData) {
            svAssets.add(SpaceVehicleAsset.convertToSpaceVehicleAsset(d));
        }
        log.debug("svAssets = " + svAssets.toString());
        
        int assetsAdded = 0;
        try {
            log.info("Logging into CODEX");
            LoginToken token = getLoginToken();
            if (token != null && (token.getToken() != null && !token.getToken().isEmpty()) && !svAssets.isEmpty()) {
                assetsAdded = sendAssetsToCodex(svAssets, token);
            }
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString());
        }
        return assetsAdded;
    }

    private LoginToken getLoginToken() throws Exception {
        Credentials creds = new Credentials();
        creds.setUser(cUsername);
        creds.setPass(cPassword);

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> acceptable = new ArrayList<>();
        acceptable.add(MediaType.APPLICATION_JSON);
        headers.setAccept(acceptable);
        HttpEntity<Credentials> entity = new HttpEntity<>(creds, headers);

        // send request and parse result
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LoginToken> loginResponse = restTemplate.exchange(loginUrl, HttpMethod.POST, entity, LoginToken.class);
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            LoginToken token = loginResponse.getBody();
            log.info("token = " + token.toString());
            return token;
        } else {
            throw new Exception(loginResponse.getStatusCode().getReasonPhrase());
        }
    }
    
    private int sendAssetsToCodex(List<SpaceVehicleAsset> assets, LoginToken token) throws Exception {
        int assetsAdded = 0;
        
        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> acceptable = new ArrayList<>();
        acceptable.add(MediaType.APPLICATION_JSON);
        headers.setAccept(acceptable);
        headers.set("X-Auth-Token", token.getToken());

        // send request and parse result
        RestTemplate restTemplate = new RestTemplate();
        for (SpaceVehicleAsset asset : assets) {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(asset);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            String answer = restTemplate.postForObject(svUrl, entity, String.class);
            if (answer != null && !answer.isEmpty()) {
                assetsAdded++;
            }
        }
        
        return assetsAdded;
    }
}
