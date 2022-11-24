package com.joaogodoi.SpringBoot3.services.utils;

import com.joaogodoi.SpringBoot3.models.Person;

import java.util.concurrent.atomic.AtomicLong;

public class MockGenerators {

    private final AtomicLong counter = new AtomicLong();

    public Person createMockPerson(Integer i) {
        Person person = new Person();
        person.setId(counter.getAndIncrement());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("São Paulo - Brazil");
        person.setGender("Male");
        return person;
    }
}
