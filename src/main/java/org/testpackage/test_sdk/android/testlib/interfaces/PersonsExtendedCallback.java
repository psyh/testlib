package org.testpackage.test_sdk.android.testlib.interfaces;

public interface PersonsExtendedCallback {
    void onResult(String persons);

    void onFail(String reason);
}