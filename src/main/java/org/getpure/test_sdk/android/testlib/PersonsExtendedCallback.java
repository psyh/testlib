package org.getpure.test_sdk.android.testlib;

import java.util.List;

/**
 * Created by buyaroff1 on 05/01/16.
 */
public interface PersonsExtendedCallback{
    void onResult(String persons);

    void onFail(String reason);
}