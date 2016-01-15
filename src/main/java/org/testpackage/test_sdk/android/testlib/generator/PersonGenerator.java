package org.testpackage.test_sdk.android.testlib.generator;

import android.content.Context;

import org.testpackage.test_sdk.android.testlib.util.Utils;
import org.testpackage.test_sdk.android.testlib.db.PersonsHolder;
import org.testpackage.test_sdk.android.testlib.interfaces.PersonsCallback;
import org.testpackage.test_sdk.android.testlib.interfaces.PersonsExtendedCallback;
import org.testpackage.test_sdk.android.testlib.interfaces.SuccessCallback;
import org.testpackage.test_sdk.android.testlib.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator {

    private Context context;

    private String[] status = {
            "none",
            "like",
            "hate",
            "removed"};

    public PersonGenerator(Context context) {
        this.context = context;
    }

    public Person getNewStatus(Person p) {
        if (p.getStatus().equals(status[0])) {
            p.setStatus(isReally(2) ? status[0] : status[getIntStatus(-1, -1)]);
        } else if (p.getStatus().equals(status[1])) {
            p.setStatus(isReally(2) ? status[1] : status[getIntStatus(0, -1)]);
        } else if (p.getStatus().equals(status[2])) {
            p.setStatus(isReally(2) ? status[2] : status[getIntStatus(0, 1)]);
        } else if (p.getStatus().equals(status[3])) {
            p.setStatus(status[3]);
        }
        return p;
    }

    private int getIntStatus(int except1, int except2) {
        try {
            int res = new Random().nextInt(status.length);
            if (res == except1 || res == except2) return getIntStatus(except1, except2);
            if (res == 1) return (isReally(2)) ? res : getIntStatus(except1, except2);
            if (res == 2) return (isReally(12)) ? res : getIntStatus(except1, except2);
            if (res == 3) return (isReally(25)) ? res : getIntStatus(except1, except2);
            else return res;
        } catch (Exception e) {
            return 3;
        }
    }

    private boolean isReally(int ver) {
        int res = new Random().nextInt(ver);
        if (res == 0) return true;
        else return false;
    }

    public Location getNewLocation() {
        return generateNewLocation();
    }

    public List<Person> createPersons() {
        List<Person> list = new ArrayList<>();
        for (int i = 1; i < photos_1.length + 1; i++) {
            try {
                list.add(createPerson(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void refreshPersons(final SuccessCallback callback) {
        final PersonsHolder personsHolder = new PersonsHolder(context);
        personsHolder.dropAllPersons(new PersonsHolder.PersonsDropCallback() {
            @Override
            public void onSuccess() {
                personsHolder.savePersons(createPersons());
                callback.onSuccess();
            }
        });
    }

    public Person createPerson(int id) throws Exception {
        if (id > 20 || id < 1) throw new Exception();
        Person data = new Person();
        data.setId(id);
        data.setLocation(getNewLocation().getString());
        int pos = id;
        data.setPhoto(photos_1[pos - 1]);
        data.setStatus("none");
        return data;
    }

    public void getPersons(int page, final PersonsExtendedCallback callback) {
        if (page < 0) {
            callback.onFail("page is not exist");
        } else {
            PersonsHolder personsHolder = new PersonsHolder(context);
            int offset = page * 10;
            personsHolder.getPortionPersons(offset, 10, new PersonsCallback() {
                @Override
                public void onResult(List<Person> persons) {
                    callback.onResult(Utils.toJson(persons));
                }
            });
        }

    }

    public Location generateNewLocation() {
        Location l = new Location();
        double lat = 38.725126 + (double) (new Random().nextInt(5)) / 99;
        double lon = -9.150010 + (double) (new Random().nextInt(5)) / 99;
        lat = Math.floor(lat * 1000000) / 1000000;
        lon = Math.floor(lon * 1000000) / 1000000;
        l.setLatitude(lat);
        l.setLongitude(lon);
        return l;
    }

    public Person changePerson(Person p) {
        p.setLocation(getNewLocation().getString());
        p.setStatus(getNewStatus(p).getStatus());
        return p;
    }


    private String[] photos_1 = {
            "http://cs313217.vk.me/v313217800/436c/DO1w-2mKStQ.jpg",
            "http://cs312726.vk.me/v312726735/fa1/0Dqyd_2ck8Q.jpg",
            "http://cs312726.vk.me/v312726012/1000/COjFH1cL6LI.jpg",
            "http://cs312231.vk.me/v312231019/2d6c/9mjAPeNZ9zo.jpg",
            "http://cs312231.vk.me/v312231019/2d0c/2XL72_m9TkU.jpg",
            "http://cs310826.vk.me/v310826012/101/wrbVFlaHy90.jpg",
            "http://cs309931.vk.me/v309931548/78e3/oalgZr5PBRU.jpg",
            "http://cs309619.vk.me/v309619012/6d78/cVq02lRJsV8.jpg",
            "http://cs309123.vk.me/v309123012/99ed/wKBHhR3jz0g.jpg",
            "http://cs309123.vk.me/v309123012/9874/rUcVGlIs5jE.jpg",
            "http://cs9299.vk.me/v9299447/2a5f/bNoKD9s8P0w.jpg",
            "http://cs309123.vk.me/v309123012/986c/aA0kmCe5Gb4.jpg",
            "http://cs309123.vk.me/v309123012/976a/_8uQQO0ONmc.jpg",
            "http://cs309123.vk.me/v309123012/9750/vShzFKMkn-M.jpg",
            "http://cs309123.vk.me/v309123012/9122/r7oKX3HSaIg.jpg",
            "http://cs309123.vk.me/v309123012/8fee/wr9iaKZxKBo.jpg",
            "http://cs307212.vk.me/v307212320/aee5/u4sNJsXyIKo.jpg",
            "http://cs9331.vk.me/v9331012/30af/SA1t3W3YKes.jpg",
            "http://cs307107.vk.me/v307107012/b36c/W9YH6T33M90.jpg",
            "http://cs305600.vk.me/v305600012/19acb/Tj6KB-ZcbiE.jpg"
    };

   public class Location {

        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getString() {
            return latitude + "," + longitude;
        }
    }
}
