package com.bits.ingest.codex;

import com.bits.ingest.spacetracker.Data;
import com.bits.ingest.util.OrbitUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class SpaceVehicleAsset {

/*
{"uuid": "string", "parentUuid": "string", "assetId": "string", "classification": "string",
 "shortName": "string", "fullName": "string", "aliases": ["string"],
 "statusReported": true, "moreInfoLink": "string", "countryTrigraph": "string",
 "alignment": "Blue", "assetType": "GroundAsset", "capabilityUuids": ["string"],
 "sscNumber": "string", "vehicleGroupName": "string", "orbit": "LOW_EARTH_ORBIT",
 "payloadUuids": ["string"]
}
*/
    
    private String assetId; //maps to Space Tracker INTLDES
    private String classification = "U"; //default for all Space Tracker objects
    private String shortName; //maps to Space Tracker SATNAME or OBJECT_NAME
    private String fullName; //maps to Space Tracker COMMENT (if not null) + LAUNCH + SITE
    private boolean statusReported; //maps to Space Tracker CURRENT
    private String countryTrigraph; //maps to Space Tracker COUNTRY
    private String alignment = "Yellow"; //default for all Space Tracker objects
    private String assetType = "SpaceVehicle"; //default for all Space Tracker objects
    private String sscNumber; //maps to Space Tracker OBJECT_NUMBER
    private String orbit; //calculated based on Space Tracker PERIOD, INCLINATION, APOGEE, and PERIGEE
    
    public SpaceVehicleAsset() {
        
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getClassification() {
        return classification;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isStatusReported() {
        return statusReported;
    }

    public void setStatusReported(boolean statusReported) {
        this.statusReported = statusReported;
    }

    public String getCountryTrigraph() {
        return countryTrigraph;
    }

    public void setCountryTrigraph(String countryTrigraph) {
        this.countryTrigraph = countryTrigraph;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getAssetType() {
        return assetType;
    }

    public String getSscNumber() {
        return sscNumber;
    }

    public void setSscNumber(String sscNumber) {
        this.sscNumber = sscNumber;
    }

    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String orbit) {
        this.orbit = orbit;
    }

    @Override
    public String toString() {
        return "SpaceVehicleAsset{" + "assetId=" + assetId + ", classification=" + classification + ", shortName=" + shortName + ", fullName=" + fullName + ", statusReported=" + statusReported + ", countryTrigraph=" + countryTrigraph + ", alignment=" + alignment + ", assetType=" + assetType + ", sscNumber=" + sscNumber + ", orbit=" + orbit + '}';
    }

    public static SpaceVehicleAsset convertToSpaceVehicleAsset(Data spaceTrackerAsset) {
        SpaceVehicleAsset asset = new SpaceVehicleAsset();
        asset.setAssetId(spaceTrackerAsset.getIntldes());
        if (spaceTrackerAsset.getCountry().equals("US")) asset.setAlignment("Blue");
        asset.setCountryTrigraph(spaceTrackerAsset.getCountry());
        StringBuilder assetName = new StringBuilder();
        if (spaceTrackerAsset.getComment() != null && !spaceTrackerAsset.getComment().isEmpty()) {
            assetName.append(spaceTrackerAsset.getComment()).append(" ");
        }
        if (spaceTrackerAsset.getLaunch() != null && !spaceTrackerAsset.getLaunch().isEmpty()) {
            assetName.append(spaceTrackerAsset.getLaunch()).append(" ");
        }
        if (spaceTrackerAsset.getSite() != null && !spaceTrackerAsset.getSite().isEmpty()) {
            assetName.append(spaceTrackerAsset.getSite());
        }
        asset.setFullName(assetName.toString());
        String period = spaceTrackerAsset.getPeriod();
        String apogee = spaceTrackerAsset.getApogee();
        String perigee = spaceTrackerAsset.getPerigee();
        if (period != null && apogee != null && perigee != null) {
            asset.setOrbit(OrbitUtils.determineOrbitType(Double.parseDouble(period), Integer.parseInt(apogee), Integer.parseInt(perigee)));
        }
        asset.setShortName(spaceTrackerAsset.getObjectName());
        asset.setSscNumber(spaceTrackerAsset.getObjectNumber());
        asset.setStatusReported(spaceTrackerAsset.getCurrent().equals("Y"));
        
        return asset;
    } 
}
