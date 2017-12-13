package com.bits.ingest;

import com.bits.ingest.spacetracker.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GetSpaceTracker {

    private static final Logger log = LoggerFactory.getLogger(GetSpaceTracker.class);
    
    @Value("${space_tracker.username}")
    private String stUsername;
    
    @Value("${space_tracker.password}")
    private String stPassword;
    
    private final String baseURL = "https://www.space-track.org";
    private final String authPath = "/auth/login";
    private final String query = "/basicspacedata/query/class/satcat/OBJECT_TYPE/PAYLOAD/DECAY/null-val/PERIOD/%3C%3Enull-val/LAUNCH/%3E2013-01-01/orderby/LAUNCH%20desc/format/json/metadata/true";

    public JsonResult getData() throws Exception {
        JsonResult jsonResult = null;
        try {
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            URL url = new URL(baseURL + authPath);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            String input = "identity=" + stUsername + "&password=" + stPassword;

            log.info("Loggin into Space Tracker");
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                log.info(output);
            }

            log.info("Executing query and receiving results");
            url = new URL(baseURL + query);
            br = new BufferedReader(new InputStreamReader((url.openStream())));
            StringBuilder jsonString = new StringBuilder();
            while ((output = br.readLine()) != null) {
                jsonString.append(output);
            }
            
            log.info("Converting JSON string to JsonResult object");
            log.debug("jsonString=" + jsonString.toString());
            ObjectMapper mapper = new ObjectMapper();
            jsonResult = mapper.readValue(jsonString.toString(), JsonResult.class);

            log.info("Logging off Space Tracker");
            url = new URL(baseURL + "/auth/logout");
            br = new BufferedReader(new InputStreamReader((url.openStream())));
            while ((output = br.readLine()) != null) {
                log.info(output);
            }
            conn.disconnect();

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString());
        }
        
        return jsonResult;
    }
}
