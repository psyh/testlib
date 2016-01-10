package org.testpackage.test_sdk.android.testlib.util;

import android.location.Location;

import com.google.gson.Gson;

import org.testpackage.test_sdk.android.testlib.model.Person;

import java.util.List;

/**
 * Created by buyaroff1 on 10/01/16.
 */
public class Utils {

    public static String toJson(Person p) {
        try {
            return new Gson().toJson(p).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String toJson(List<Person> p) {
        try {
            return new Gson().toJson(p).toString();
        } catch (Exception e) {
            return null;
        }
    }

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
