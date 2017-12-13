package com.bits.ingest.util;

public class OrbitUtils {
    
    public static final double LEO_PERIOD_LOWER_BOUND = 84d;
    public static final double LEO_PERIOD_UPPER_BOUND = 128d;
    public static final double MEO_PERIOD_LOWER_BOUND = 128d;
    public static final double MEO_PERIOD_UPPER_BOUND = 1440d;
    public static final double GEO_PERIOD = 1440d;
    private static final double PERIOD_CLOSE_FACTOR = 0.01;
    private static final double GEO_PERIOD_LOWER_BOUND = (GEO_PERIOD - (GEO_PERIOD * PERIOD_CLOSE_FACTOR));
    private static final double GEO_PERIOD_UPPER_BOUND = (GEO_PERIOD + (GEO_PERIOD * PERIOD_CLOSE_FACTOR));
    
    public static final int LEO_APOGEE_LOWER_BOUND = 160;
    public static final int LEO_APOGEE_UPPER_BOUND = 2000;
    public static final int MEO_APOGEE_LOWER_BOUND = 2000;
    public static final int MEO_APOGEE_UPPER_BOUND = 35786;
    public static final int GEO_APOGEE = 35786;
    private static final double APOGEE_CLOSE_FACTOR = 0.01;
    private static final int GEO_APOGEE_LOWER_BOUND = (GEO_APOGEE - ((int)(GEO_APOGEE * APOGEE_CLOSE_FACTOR)));
    private static final int GEO_APOGEE_UPPER_BOUND = (GEO_APOGEE + ((int)(GEO_APOGEE * APOGEE_CLOSE_FACTOR)));
    
    public static final String LEO = "LOW_EARTH_ORBIT";
    public static final String MEO = "MEDIUM_EARTH_ORBIT";
    public static final String GEO = "GEOSYNCHRONOUS_ORBIT";
    public static final String HEO = "HIGHLY_ELLIPTICAL_ORBIT";
    
    public static String determineOrbitType(double period, int apogee, int perigee) {
        //check for GEO first
        if (GEO_PERIOD_LOWER_BOUND < period && period < GEO_PERIOD_UPPER_BOUND) {
            if (GEO_APOGEE_LOWER_BOUND < apogee && apogee < GEO_APOGEE_UPPER_BOUND) {
                if (Math.abs((apogee - perigee) / apogee) < APOGEE_CLOSE_FACTOR) {
                    return GEO;
                }
            }
        }
        
        //check HEO next
        if (period > GEO_PERIOD) {
            if (apogee > GEO_APOGEE) {
                return HEO;
            }
        }
        
        //check LEO next
        if (LEO_PERIOD_LOWER_BOUND < period && period < LEO_PERIOD_UPPER_BOUND) {
            if (LEO_APOGEE_LOWER_BOUND < apogee && apogee < LEO_APOGEE_UPPER_BOUND) {
                return LEO;
            }
        }
        
        //else default to MEO
        return MEO;
    }
    
}
