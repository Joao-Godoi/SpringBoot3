package com.joaogodoi.SpringBoot3.services;

import com.joaogodoi.SpringBoot3.data.vo.v1.PersonVO;
import com.joaogodoi.SpringBoot3.exceptions.handler.ResourceNotFoundException;
import com.joaogodoi.SpringBoot3.mapper.DozerMapper;
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

    public PersonVO findById(Long id) {
        logger.info("Finding one person...");
        var person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + id));
        return DozerMapper.parseObject(person, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people...");
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating a new person...");
        var person = DozerMapper.parseObject(personVO, Person.class);
        return DozerMapper.parseObject(personRepository.save(person), PersonVO.class);
    }

    public PersonVO update(PersonVO personVO) {
        logger.info("Updating a person...");
        var entity = personRepository.findById(personVO.getId()).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + personVO.getId()));
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting person " + id + "...");
        var personToDelete = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No person found for id: " + id));
        personRepository.delete(personToDelete);
    }

}
