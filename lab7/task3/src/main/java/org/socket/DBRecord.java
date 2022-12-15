package org.socket;

import java.util.Comparator;

public class DBRecord {
    private int id;
    private String name;
    private int age;

    DBRecord(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public static Comparator<DBRecord> sortById = new Comparator<DBRecord>() {
        @Override
        public int compare(DBRecord obj1, DBRecord obj2) {
            return obj1.id-obj2.id;
        }
    };
    public static Comparator<DBRecord> sortByName = new Comparator<DBRecord>() {
        @Override
        public int compare(DBRecord obj1, DBRecord obj2) {
            return obj1.name.compareTo(obj2.name);
//            return obj2.name.compareTo(obj1.name);
        }
    };
    //sort by age
    public static Comparator<DBRecord> sortByAge = new Comparator<DBRecord>() {
        @Override
        public int compare(DBRecord obj1, DBRecord obj2) {
            return obj1.age-obj2.age;
        }
    };
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
