package org.testpackage.test_sdk.android.testlib;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.testpackage.test_sdk.android.testlib.generator.PersonGenerator;
import org.testpackage.test_sdk.android.testlib.model.Person;
import org.junit.Test;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void testStatus() {
        Person p = new Person();
        p.setStatus("none");

       /* for (int i = 0; i < 100; i++) {
            new PersonGenerator().getNewStatus(p).getStatus();
            //assertFalse("none".equals(new PersonGenerator().getNewStatus(p).getStatus()));
        }*/
    }
    @Test(expected = Exception.class)
    public void testCreatePerson() throws Exception {
           // new PersonGenerator(getApplication()).createPerson(-1);
    }

    @Test
    public void testNewLocation() {
        PersonGenerator.Location l = new PersonGenerator(getContext()).getNewLocation();
        for (int i = 0; i < 10; i++) {
            PersonGenerator.Location ll = new PersonGenerator(getContext()).getNewLocation();
            //float distance = l.distanceTo(ll);
//            assertTrue(distance != 0);
            //assertFalse("none".equals(new PersonGenerator().getNewStatus(p).getStatus()));
        }
    }
}