package org.testpackage.test_sdk.android.testlib;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

import org.testpackage.test_sdk.android.testlib.db.PersonsHolder;
import org.testpackage.test_sdk.android.testlib.generator.PersonGenerator;
import org.testpackage.test_sdk.android.testlib.interfaces.PersonsCallback;
import org.testpackage.test_sdk.android.testlib.interfaces.SuccessCallback;
import org.testpackage.test_sdk.android.testlib.model.Person;
import org.junit.Test;

import java.util.List;

public class PersonsHolderTests extends ApplicationTestCase<Application> {
    public PersonsHolderTests() {
        super(Application.class);
    }

    @Test
    public void test_createPersons() {
        Context context = getContext();
        PersonGenerator personGenerator = new PersonGenerator(context);
        List<Person> persons = personGenerator.createPersons();
        assertTrue(persons != null);
        assertTrue(!persons.isEmpty());
        assertTrue(persons.size() == 20);
        assertTrue(persons.get(2) != null);
        assertTrue(persons.get(9).getId() == 10);
        assertTrue(persons.get(19).getId() == 20);
    }


    @Test
    public void test_refreshPersons() {
        Context context = getContext();
        final PersonGenerator personGenerator = new PersonGenerator(context);
        final PersonsHolder personsHolder = new PersonsHolder(getContext());
        personsHolder.dropAllPersons(new PersonsHolder.PersonsDropCallback() {
            @Override
            public void onSuccess() {
                personsHolder.getAllPersons(new PersonsCallback() {
                    @Override
                    public void onResult(List<Person> personsss) {
                        assertTrue(personsss != null);
                        assertTrue(personsss.isEmpty());

                        personGenerator.refreshPersons(new SuccessCallback() {
                            @Override
                            public void onSuccess() {
                                personsHolder.getAllPersons(new PersonsCallback() {
                                    @Override
                                    public void onResult(List<Person> personsss) {
                                        assertTrue(personsss != null);
                                        assertTrue(!personsss.isEmpty());
                                        assertTrue(personsss.size() == 20);
                                        assertTrue(personsss.get(2) != null);
                                        assertTrue(personsss.get(9).getId() == 10);
                                        assertTrue(personsss.get(19).getId() == 20);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    @Test
    public void test_getPortionPersons() {
        API.INSTANCE.init(getContext());
        API.INSTANCE.refreshPersons(new SuccessCallback() {
            @Override
            public void onSuccess() {
                PersonsHolder personsHolder = new PersonsHolder(getContext());
                personsHolder.getPortionPersons(10, 10, new PersonsCallback() {
                    @Override
                    public void onResult(List<Person> persons) {
                        assertTrue(persons != null);
                        assertTrue(!persons.isEmpty());
                        assertTrue(persons.size() == 10);
                    }
                });
            }
        });
    }

    @Test
    public void test_getPersonById() {
        API.INSTANCE.init(getContext());
        API.INSTANCE.refreshPersons(new SuccessCallback() {
            @Override
            public void onSuccess() {
                int ID = 2;
                PersonsHolder personsHolder = new PersonsHolder(getContext());
                Person person = personsHolder.getPersonById(ID);
                assertTrue(person != null);
                assertTrue(person.getId() == ID);
            }
        });
    }

    @Test
    public void test_changePerson() {
        API.INSTANCE.init(getContext());
        API.INSTANCE.refreshPersons(new SuccessCallback() {
            @Override
            public void onSuccess() {
                int ID = 2;
                PersonsHolder personsHolder = new PersonsHolder(getContext());
                Person person = personsHolder.getPersonById(ID);
                String locationFirst = person.getLocation();
                Person personNew = new PersonGenerator(getContext()).changePerson(person);
                String locationSecond = personNew.getLocation();
                assertTrue(person.getId() == personNew.getId());
                assertFalse(locationFirst.equals(locationSecond));
                personsHolder.savePerson(personNew);
                Person personSaved = personsHolder.getPersonById(ID);
                String locationSaved = personSaved.getLocation();
                assertTrue(person.getId() == personSaved.getId());
                assertTrue(locationSecond.equals(locationSaved));
                assertFalse(locationFirst.equals(locationSaved));
            }
        });
    }

    @Test
    public void testDropAllPersons() {
        final PersonsHolder personsHolder = new PersonsHolder(getContext());
        personsHolder.dropAllPersons(new PersonsHolder.PersonsDropCallback() {
            @Override
            public void onSuccess() {
                personsHolder.getAllPersons(new PersonsCallback() {
                    @Override
                    public void onResult(List<Person> personsss) {
                        assertTrue(personsss != null);
                        assertTrue(personsss.isEmpty());
                    }
                });
            }
        });
    }
}
