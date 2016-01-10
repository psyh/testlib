package org.getpure.test_sdk.android.testlib;

import android.content.Context;

public enum API {
    INSTANCE;

    Context context;

    public void init(Context context) {
        this.context = context;
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
