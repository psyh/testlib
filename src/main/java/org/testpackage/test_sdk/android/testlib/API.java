package org.testpackage.test_sdk.android.testlib;

import android.content.Context;

import org.testpackage.test_sdk.android.testlib.db.DatabaseHelper;
import org.testpackage.test_sdk.android.testlib.generator.PersonGenerator;
import org.testpackage.test_sdk.android.testlib.interfaces.PersonsExtendedCallback;
import org.testpackage.test_sdk.android.testlib.interfaces.SuccessCallback;
import org.testpackage.test_sdk.android.testlib.services.UpdateService;

public enum API {
    INSTANCE;

    Context context;

    public void init(Context applicationContext) {
        this.context = applicationContext;
        DatabaseHelper.getInstance(context);
        UpdateService.INSTANCE.startService(context);
    }

    public void refreshPersons(SuccessCallback successCallback) {
        new PersonGenerator(context).refreshPersons(successCallback);
    }

    public void getPersons(int page, PersonsExtendedCallback callback) {
        new PersonGenerator(context).getPersons(page, callback);
    }

    public void subscribeUpdates(UpdateService.UpdateServiceListener listener) {
        UpdateService.INSTANCE.setListener(listener);
    }

    public void unSubscribeUpdates() {
        UpdateService.INSTANCE.stopService();
    }
}
