package org.testpackage.test_sdk.android.testlib.services;

import android.content.Context;

import org.testpackage.test_sdk.android.testlib.util.Utils;
import org.testpackage.test_sdk.android.testlib.db.PersonsHolder;
import org.testpackage.test_sdk.android.testlib.generator.PersonGenerator;
import org.testpackage.test_sdk.android.testlib.interfaces.PersonsCallback;
import org.testpackage.test_sdk.android.testlib.model.Person;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import bolts.Task;

public enum UpdateService {

    INSTANCE;

    public boolean work = false;

    Context context;
    UpdateServiceListener listener;

    public void setListener(UpdateServiceListener listener) {
        this.listener = listener;
        if (!work && context != null) startService(context);
    }

    public void startService(Context context) {
        this.context = context;
        work = true;
        Task.callInBackground(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                while (work) {
                    Thread.sleep(2000);
                    checkPersons();
                }
                return null;
            }
        });
    }

    public void stopService() {
        work = false;
    }

    public void checkPersons() {
        new PersonsHolder(context).getAllPersons(new PersonsCallback() {
            @Override
            public void onResult(List<Person> persons) {
                if (persons != null && !persons.isEmpty()) {
                    Person p = getRandomPerson(persons);
                    p = new PersonGenerator(context).changePerson(p);
                    new PersonsHolder(context).savePerson(p);
                    listener.onChanges(Utils.toJson(p));
                }
            }
        });
    }


    public Person getRandomPerson(List<Person> persons) {
        int pos = new Random().nextInt(persons.size());
        return persons.get(pos);
    }

    public interface UpdateServiceListener {
        void onChanges(String person);
    }
}
