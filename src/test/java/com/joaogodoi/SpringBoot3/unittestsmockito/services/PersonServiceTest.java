package com.joaogodoi.SpringBoot3.unittestsmockito.services;

import com.joaogodoi.SpringBoot3.data.vo.v1.PersonVO;
import com.joaogodoi.SpringBoot3.exceptions.RequiredObjectIsNullException;
import com.joaogodoi.SpringBoot3.models.Person;
import com.joaogodoi.SpringBoot3.repositories.PersonRepository;
import com.joaogodoi.SpringBoot3.services.PersonService;
import com.joaogodoi.SpringBoot3.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson inputObject;
    @Mock
    PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() throws Exception {
        inputObject = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Person person = inputObject.mockEntity(1);
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        var response = personService.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getKey());
        assertNotNull(response.getLinks());
        assertTrue(response.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
        assertEquals("Address Test1", response.getAddress());
        assertEquals("First Name Test1", response.getFirstName());
        assertEquals("Last Name Test1", response.getLastName());
        assertEquals("Female", response.getGender());
    }

    @Test
    void testFindAll() {
        List<Person> personList = inputObject.mockEntityList();
        when(personRepository.findAll()).thenReturn(personList);

        var response = personService.findAll();
        assertNotNull(response);
        assertEquals(14, response.size());

        var firstPerson = response.get(1);
        assertNotNull(firstPerson.getKey());
        assertNotNull(firstPerson.getLinks());
        assertTrue(firstPerson.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
        assertEquals("Address Test1", firstPerson.getAddress());
        assertEquals("First Name Test1", firstPerson.getFirstName());
        assertEquals("Last Name Test1", firstPerson.getLastName());
        assertEquals("Female", firstPerson.getGender());

        var seventhPerson = response.get(7);
        assertNotNull(seventhPerson.getKey());
        assertNotNull(seventhPerson.getLinks());
        assertTrue(seventhPerson.toString().contains("links: [</api/v1/person/7>;rel=\"self\"]"));
        assertEquals("Address Test7", seventhPerson.getAddress());
        assertEquals("First Name Test7", seventhPerson.getFirstName());
        assertEquals("Last Name Test7", seventhPerson.getLastName());
        assertEquals("Female", seventhPerson.getGender());
    }

    @Test
    void testCreate() {
        Person person = inputObject.mockEntity(1);

        Person persistedPerson = person;
        persistedPerson.setId(1L);

        PersonVO personVO = inputObject.mockVO(1);
        personVO.setKey(1L);

        when(personRepository.save(person)).thenReturn(persistedPerson);

        var response = personService.create(personVO);
        assertNotNull(response);
        assertNotNull(response.getKey());
        assertNotNull(response.getLinks());
        assertTrue(response.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
        assertEquals("Address Test1", response.getAddress());
        assertEquals("First Name Test1", response.getFirstName());
        assertEquals("Last Name Test1", response.getLastName());
        assertEquals("Female", response.getGender());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> personService.create(null));
        String expectedMessage = "It is not allowed to persist a null object!";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testUpdate() {
        Person person = inputObject.mockEntity(1);
        person.setId(1L);

        Person persistedPerson = person;
        persistedPerson.setId(1L);

        PersonVO personVO = inputObject.mockVO(1);
        personVO.setKey(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(persistedPerson);

        var response = personService.update(personVO);
        assertNotNull(response);
        assertNotNull(response.getKey());
        assertNotNull(response.getLinks());
        assertTrue(response.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
        assertEquals("Address Test1", response.getAddress());
        assertEquals("First Name Test1", response.getFirstName());
        assertEquals("Last Name Test1", response.getLastName());
        assertEquals("Female", response.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> personService.update(null));
        String expectedMessage = "It is not allowed to persist a null object!";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testDelete() {
        Person person = inputObject.mockEntity(1);
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        personService.delete(1L);
    }

}
