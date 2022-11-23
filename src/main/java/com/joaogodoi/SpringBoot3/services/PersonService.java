package com.joaogodoi.SpringBoot3.services;

import com.joaogodoi.SpringBoot3.models.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

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
}
