package com.bits.ingest;

import com.bits.ingest.spacetracker.Data;
import com.bits.ingest.spacetracker.JsonResult;
import com.bits.ingest.spacetracker.RequestMetadata;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpaceVehicleIngestApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpaceVehicleIngestApplication.class);

    @Autowired
    private GetSpaceTracker vehicles;
    
    @Autowired
    private PopulateCodex codex;

    public static void main(String[] args) {
        SpringApplication.run(SpaceVehicleIngestApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            JsonResult jsonResult = this.vehicles.getData();
            if (jsonResult != null) {
                log.debug("jsonResult=" + jsonResult);
                RequestMetadata metadata = jsonResult.getRequestMetadata();
                long rowsReturned = metadata.getReturnedRows();
                List<Data> spaceTrackerData =  jsonResult.getData();
                if (spaceTrackerData != null && !spaceTrackerData.isEmpty()) {
                    long rowsProcessed = codex.processData(spaceTrackerData);
                    log.info("Number of rows returned from Space Tracker: " + rowsReturned);
                    log.info("Number of rows posted to CODEX:             " + rowsProcessed);
                }
            }
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString());
        }
    }

}
