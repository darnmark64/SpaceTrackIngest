package com.bits.ingest.spacetracker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Data {

/*
"INTLDES":"2017-077A","NORAD_CAT_ID":"43034","OBJECT_TYPE":"PAYLOAD","SATNAME":"LKW-1","COUNTRY":"PRC","LAUNCH":"2017-12-03","SITE":"JSC","DECAY":null,
"PERIOD":"94.53","INCLINATION":"97.46","APOGEE":"503","PERIGEE":"489","COMMENT":null,"COMMENTCODE":null,"RCSVALUE":"0","RCS_SIZE":null,"FILE":"6392",
"LAUNCH_YEAR":"2017","LAUNCH_NUM":"77","LAUNCH_PIECE":"A","CURRENT":"Y","OBJECT_NAME":"LKW-1","OBJECT_ID":"2017-077A","OBJECT_NUMBER":"43034"
*/
    @JsonProperty("INTLDES")
    private String intldes;
    @JsonProperty("NORAD_CAT_ID")
    private String noradCatId;
    @JsonProperty("OBJECT_TYPE")
    private String objectType;
    @JsonProperty("SATNAME")
    private String satname;
    @JsonProperty("COUNTRY")
    private String country;
    @JsonProperty("LAUNCH")
    private String launch;
    @JsonProperty("SITE")
    private String site;
    @JsonProperty("DECAY")
    private String decay;
    @JsonProperty("PERIOD")
    private String period;
    @JsonProperty("INCLINATION")
    private String inclination;
    @JsonProperty("APOGEE")
    private String apogee;
    @JsonProperty("PERIGEE")
    private String perigee;
    @JsonProperty("COMMENT")
    private String comment;
    @JsonProperty("COMMENTCODE")
    private String commentcode;
    @JsonProperty("RCSVALUE")
    private String rcsvalue;
    @JsonProperty("RCS_SIZE")
    private String rcsSize;
    @JsonProperty("FILE")
    private String file;
    @JsonProperty("LAUNCH_YEAR")
    private String launchYear;
    @JsonProperty("LAUNCH_NUM")
    private String launchNum;
    @JsonProperty("LAUNCH_PIECE")
    private String launchPiece;
    @JsonProperty("CURRENT")
    private String current;
    @JsonProperty("OBJECT_NAME")
    private String objectName;
    @JsonProperty("OBJECT_ID")
    private String objectId;
    @JsonProperty("OBJECT_NUMBER")
    private String objectNumber;
    
    public Data() {
    }

    public String getIntldes() {
        return intldes;
    }

    public void setIntldes(String intldes) {
        this.intldes = intldes;
    }

    public String getNoradCatId() {
        return noradCatId;
    }

    public void setNoradCatId(String noradCatId) {
        this.noradCatId = noradCatId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getSatname() {
        return satname;
    }

    public void setSatname(String satname) {
        this.satname = satname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLaunch() {
        return launch;
    }

    public void setLaunch(String launch) {
        this.launch = launch;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDecay() {
        return decay;
    }

    public void setDecay(String decay) {
        this.decay = decay;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getInclination() {
        return inclination;
    }

    public void setInclination(String inclination) {
        this.inclination = inclination;
    }

    public String getApogee() {
        return apogee;
    }

    public void setApogee(String apogee) {
        this.apogee = apogee;
    }

    public String getPerigee() {
        return perigee;
    }

    public void setPerigee(String perigee) {
        this.perigee = perigee;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentcode() {
        return commentcode;
    }

    public void setCommentcode(String commentcode) {
        this.commentcode = commentcode;
    }

    public String getRcsvalue() {
        return rcsvalue;
    }

    public void setRcsvalue(String rcsvalue) {
        this.rcsvalue = rcsvalue;
    }

    public String getRcsSize() {
        return rcsSize;
    }

    public void setRcsSize(String rcsSize) {
        this.rcsSize = rcsSize;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(String launchYear) {
        this.launchYear = launchYear;
    }

    public String getLaunchNum() {
        return launchNum;
    }

    public void setLaunchNum(String launchNum) {
        this.launchNum = launchNum;
    }

    public String getLaunchPiece() {
        return launchPiece;
    }

    public void setLaunchPiece(String launchPiece) {
        this.launchPiece = launchPiece;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    @Override
    public String toString() {
        return "Data{" + "intldes='" + intldes + "', noradCatId='" + noradCatId + "', objectType='" + objectType + "', satname='" + satname + "'"
                + ", country='" + country + "', launch='" + launch + "', site='" + site + "', decay='" + decay + "', period='" + period + "'"
                + ", inclination='" + inclination + "', apogee='" + apogee + "', perigee='" + perigee + "', comment='" + comment + "'"
                + ", commentcode='" + commentcode + "', rcsvalue='" + rcsvalue + "', rcsSize='" + rcsSize + "', file='" + file + "'"
                + ", launchYear='" + launchYear + "', launchNum='" + launchNum + "', launchPiece='" + launchPiece + "', current='" + current + "'"
                + ", objectName='" + objectName + "', objectId='" + objectId + "', objectNumber='" + objectNumber + "'}";
    }

}
