package org.getpure.test_sdk.android.testlib;

import com.google.gson.Gson;

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

}
