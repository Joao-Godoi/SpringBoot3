package com.joaogodoi.SpringBoot3.services;

import com.joaogodoi.SpringBoot3.exceptions.handler.ResourceNotFoundException;
import com.joaogodoi.SpringBoot3.models.Person;
import com.joaogodoi.SpringBoot3.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person findById(Long id) {
        logger.info("Finding one person...");
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + id));
    }

    public List<Person> findAll() {
        logger.info("Finding all people...");
        return personRepository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating a new person...");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating a person...");
        Person oldPerson = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + person.getId()));
        oldPerson.setFirstName(person.getFirstName());
        oldPerson.setLastName(person.getLastName());
        oldPerson.setAddress(person.getAddress());
        oldPerson.setGender(person.getGender());

        return personRepository.save(oldPerson);
    }

    public void delete(Long id) {
        logger.info("Deleting person " + id + "...");
        Person personToDelete = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + id));
        personRepository.delete(personToDelete);
    }

}
