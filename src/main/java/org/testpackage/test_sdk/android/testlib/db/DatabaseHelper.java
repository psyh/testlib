package org.testpackage.test_sdk.android.testlib.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.testpackage.test_sdk.android.testlib.model.Person;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "soul_test_dev.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Person, Integer> simpleDao = null;
    private RuntimeExceptionDao<Person, Integer> simpleRuntimeDao = null;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static volatile DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        DatabaseHelper localInstance = instance;
        if (localInstance == null) {
            synchronized (DatabaseHelper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DatabaseHelper(context);
                }
            }
        }
        return localInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Person.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Person.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Person, Integer> getDao() throws SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(Person.class);
        }
        return simpleDao;
    }

    public RuntimeExceptionDao<Person, Integer> getPersonDataDao() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(Person.class);
        }
        return simpleRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        simpleDao = null;
        simpleRuntimeDao = null;
    }
}

