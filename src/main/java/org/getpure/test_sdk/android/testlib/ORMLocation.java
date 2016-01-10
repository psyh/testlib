package org.getpure.test_sdk.android.testlib;

import android.location.Location;

/**
 * Created by buyaroff1 on 05/01/16.
 */
public class ORMLocation {

    public static String getString(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        return lat + "," + lon;
    }

    public static Location getLocation(String location) {
        String[] arr = location.split(",");
        double lat = Double.parseDouble(arr[0]);
        double lon = Double.parseDouble(arr[1]);
        Location l = new Location("");
        l.setLongitude(lon);
        l.setLatitude(lat);
        return l;
    }
}
