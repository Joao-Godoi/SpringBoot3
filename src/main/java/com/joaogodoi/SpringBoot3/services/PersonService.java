package com.joaogodoi.SpringBoot3.services;

import com.joaogodoi.SpringBoot3.models.Person;
import com.joaogodoi.SpringBoot3.services.utils.MockGenerators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());
    private final MockGenerators mockGenerators = new MockGenerators();

    public Person findById(String id) {
        logger.info("Finding one person...");
        Person person = new Person();
        person.setId(counter.getAndIncrement());
        person.setFirstName("João");
        person.setLastName("Godoi");
        person.setAddress("São Paulo - Brazil");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll() {
        List<Person> personList = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = mockGenerators.createMockPerson(i);
            personList.add(person);
        }
        logger.info("Finding all people...");
        return personList;
    }

    public Person create(Person person) {
        logger.info("Creating a new person...");
        return person;
    }

    public Person update(Person person) {
        logger.info("Updating a person...");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting person " + id + "...");
    }

}
