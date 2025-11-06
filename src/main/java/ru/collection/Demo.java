package ru.collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        birthday.set(1995, Calendar.APRIL, 17);
        User userOne = new User("Nastya", 1, birthday);
        User userTwo = new User("Nastya", 1, birthday);

        Map<User, Object> map = new HashMap<>();

        map.put(userOne, new Object());
        map.put(userTwo, new Object());

        System.out.println("Size map: " + map.size());
        System.out.println(map);

    }
}
