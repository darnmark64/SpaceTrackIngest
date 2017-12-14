package com.bits.ingest;

import com.bits.ingest.spacetracker.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class GetSpaceTracker {

    private static final Logger log = LoggerFactory.getLogger(GetSpaceTracker.class);
    
    @Value("${space_tracker.username}")
    private String stUsername;
    
    @Value("${space_tracker.password}")
    private String stPassword;
    
    private final String baseURL = "https://www.space-track.org";
    private final String authPath = "/ajaxauth/login";
    private final String query = "/basicspacedata/query/class/satcat/OBJECT_TYPE/PAYLOAD/DECAY/null-val/PERIOD/%3C%3Enull-val/LAUNCH/%3E2013-01-01/orderby/LAUNCH%20desc/format/json/metadata/true";

    public JsonResult getData() throws Exception {
        JsonResult jsonResult = null;
        try {
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("identity", stUsername);
            map.add("password", stPassword);
            map.add("query", baseURL + query);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            log.info("Logging into space-track.org and executing query");
            ResponseEntity<String> response = template.postForEntity(baseURL + authPath, request, String.class);
            
            log.info("Converting result into JsonResult object");
            ObjectMapper mapper = new ObjectMapper();
            jsonResult = mapper.readValue(response.getBody(), JsonResult.class);
            log.debug("jsonResult = " + jsonResult);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString());
        }
        
        return jsonResult;
    }
}
