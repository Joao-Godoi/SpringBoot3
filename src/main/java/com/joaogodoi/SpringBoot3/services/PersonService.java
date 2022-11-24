package com.joaogodoi.SpringBoot3.services;

import com.joaogodoi.SpringBoot3.controllers.PersonController;
import com.joaogodoi.SpringBoot3.data.vo.v1.PersonVO;
import com.joaogodoi.SpringBoot3.exceptions.handler.ResourceNotFoundException;
import com.joaogodoi.SpringBoot3.mapper.DozerMapper;
import com.joaogodoi.SpringBoot3.models.Person;
import com.joaogodoi.SpringBoot3.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public PersonVO findById(Long id) {
        logger.info("Finding one person...");
        var person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + id));
        var personVO = DozerMapper.parseObject(person, PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people...");
        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating a new person...");
        var person = DozerMapper.parseObject(personVO, Person.class);
        var newPersonVO = DozerMapper.parseObject(personRepository.save(person), PersonVO.class);
        newPersonVO.add(linkTo(methodOn(PersonController.class).findById(newPersonVO.getKey())).withSelfRel());
        return newPersonVO;
    }

    public PersonVO update(PersonVO personVO) {
        logger.info("Updating a person...");
        var entity = personRepository.findById(personVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + personVO.getKey()));
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        var updatedPersonVO = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        updatedPersonVO.add(linkTo(methodOn(PersonController.class).findById(updatedPersonVO.getKey())).withSelfRel());
        return updatedPersonVO;
    }

    public void delete(Long id) {
        logger.info("Deleting person " + id + "...");
        var personToDelete = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + id));
        personRepository.delete(personToDelete);
    }

}
