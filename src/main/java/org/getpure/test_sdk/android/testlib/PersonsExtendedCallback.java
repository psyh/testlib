package org.getpure.test_sdk.android.testlib;

public interface PersonsExtendedCallback {
    void onResult(String persons);

    void onFail(String reason);
}