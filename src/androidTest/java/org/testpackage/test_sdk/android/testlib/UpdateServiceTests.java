package org.testpackage.test_sdk.android.testlib;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.google.gson.Gson;

import org.testpackage.test_sdk.android.testlib.generator.PersonGenerator;
import org.testpackage.test_sdk.android.testlib.model.Person;
import org.testpackage.test_sdk.android.testlib.services.UpdateService;
import org.junit.Test;

import java.util.List;

public class UpdateServiceTests extends ApplicationTestCase<Application> {
    public UpdateServiceTests() {
        super(Application.class);
    }


    @Test
    public void test_startService() {
        UpdateService service = UpdateService.INSTANCE;
        service.startService(getContext());
        service.setListener(new UpdateService.UpdateServiceListener() {
            @Override
            public void onChanges(String json) {
                Person person = new Gson().fromJson(json, Person.class);
                assertTrue(person != null);
            }
        });
        assertTrue(UpdateService.INSTANCE.work);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UpdateService.INSTANCE.stopService();
        assertFalse(UpdateService.INSTANCE.work);
    }

    @Test
    public void test_getRandomPerson() {
        UpdateService service = UpdateService.INSTANCE;
        service.setListener(new UpdateService.UpdateServiceListener() {
            @Override
            public void onChanges(String json) {
                Person person = new Gson().fromJson(json, Person.class);
                assertTrue(person != null);
            }
        });
        service.checkPersons();
    }


    @Test
    public void test_checkPersons() {
        UpdateService service = UpdateService.INSTANCE;
        List<Person> list = new PersonGenerator(getContext()).createPersons();
        for (int i = 0; i < 50; i++) {
            Person p = service.getRandomPerson(list);
            assertFalse(p.getId() > 20);
        }
    }

}
