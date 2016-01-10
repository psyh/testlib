package org.testpackage.test_sdk.android.testlib.db;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.testpackage.test_sdk.android.testlib.interfaces.PersonsCallback;
import org.testpackage.test_sdk.android.testlib.model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

public class PersonsHolder {


    private Context context;

    public PersonsHolder(Context context) {
        this.context = context;
    }

    public void savePersons(List<Person> persons) {
        for (Person p : persons) {
            savePerson(p);
        }
    }

    public void savePerson(Person p) {
        RuntimeExceptionDao<Person, Integer> dao = DatabaseHelper.getInstance(context).getPersonDataDao();
        dao.createOrUpdate(p);
    }

    public Person getPersonById(int id) {
        RuntimeExceptionDao<Person, Integer> dao = DatabaseHelper.getInstance(context).getPersonDataDao();
        QueryBuilder qb = dao.queryBuilder();
        Person person = null;
        try {
            person = (Person) qb.where().eq("id", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void dropAllPersons(final PersonsDropCallback callback) {
        getAllPersons(new PersonsCallback() {
            @Override
            public void onResult(List<Person> persons) {
                RuntimeExceptionDao<Person, Integer> dao = DatabaseHelper.getInstance(context).getPersonDataDao();
                dao.delete(persons);
                callback.onSuccess();
            }
        });
    }

    public void getAllPersons(final PersonsCallback callback) {
        Task.call(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(4000);
                return null;
            }
        }).onSuccess(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                RuntimeExceptionDao<Person, Integer> dao = DatabaseHelper.getInstance(context).getPersonDataDao();
                callback.onResult(dao.queryForAll());
                return null;
            }
        });
    }

    public void getPortionPersons(final int offset, final int limit, final PersonsCallback callback) {
        Task.call(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(4000);
                return null;
            }
        }).onSuccess(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                RuntimeExceptionDao<Person, Integer> dao = DatabaseHelper.getInstance(context).getPersonDataDao();
                callback.onResult(dao.queryBuilder().offset(offset).limit(limit).query());
                return null;
            }
        });


    }


    public interface PersonsDropCallback {
        void onSuccess();
    }
}
