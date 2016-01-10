package org.getpure.test_sdk.android.testlib.interfaces;

import org.getpure.test_sdk.android.testlib.model.Person;

import java.util.List;

public interface PersonsCallback {
    void onResult(List<Person> persons);
}