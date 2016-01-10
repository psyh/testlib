package org.getpure.test_sdk.android.testlib;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by buyaroff1 on 05/01/16.
 */
public class APITest extends ApplicationTestCase<Application> {
    public APITest() {
        super(Application.class);
    }


    @Test
    public void test_init(Context applicationContext) {
    }

    @Test
    public void test_refreshPersons() {
        API.INSTANCE.init(getContext());
        API.INSTANCE.refreshPersons(new SuccessCallback() {
            @Override
            public void onSuccess() {
                final PersonsHolder personsHolder = new PersonsHolder(getContext());
                personsHolder.getAllPersons(new PersonsCallback() {
                    @Override
                    public void onResult(List<Person> personsss) {
                        assertTrue(!personsss.isEmpty());
                    }
                });
            }
        });
    }

    @Test
    public void test_getPersons() {
        API.INSTANCE.init(getContext());
        API.INSTANCE.getPersons(0, new PersonsExtendedCallback() {
            @Override
            public void onResult(String json) {
                Type listType = new TypeToken<ArrayList<Person>>() {}.getType();
                List<Person> persons = new Gson().fromJson(json, listType);
                assertTrue(persons != null);
                assertTrue(!persons.isEmpty());
                //assertTrue(persons.size() == 10);
            }

            @Override
            public void onFail(String reason) {
                assertTrue("page is not exist".equals(reason));
            }
        });
    }

    @Test
    public void test_service() {
        API.INSTANCE.init(getContext());
        API.INSTANCE.subscribeUpdates(new UpdateService.UpdateServiceListener() {
            @Override
            public void onChanges(String json) {
                Person person = new Gson().fromJson(json, Person.class);
                assertTrue(person != null);
            }
        });
        assertTrue(UpdateService.INSTANCE.work);

        API.INSTANCE.unSubscribeUpdates();
        assertFalse(UpdateService.INSTANCE.work);
    }
}
