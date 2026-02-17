package com.laedson.person_api.service;

import com.laedson.person_api.dto.request.MessageResponseDTO;
import com.laedson.person_api.dto.request.PersonDTO;
import com.laedson.person_api.entity.Person;

import com.laedson.person_api.exception.PersonNotFoundExeption;
import com.laedson.person_api.mapper.PersonMapper;
import com.laedson.person_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse("Created Person with Id ", savedPerson.getId());
    }

    public List<PersonDTO> listAll() {
        List<Person> persons =  personRepository.findAll();
        return persons.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExeption {
        // Optional<Person> personResponse = personRepository.findById(id);

        Person personResponse = verifyIfExists(id);

        return personMapper.toDTO(personResponse);
    }

    public void deleteById(Long id) throws PersonNotFoundExeption {

         verifyIfExists(id);

         personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundExeption {

        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);

        return createMessageResponse("Updated Person with Id ", updatedPerson.getId());
    }


    private static MessageResponseDTO createMessageResponse(String x, Long id) {
        return MessageResponseDTO
                .builder()
                .message(x + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundExeption {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundExeption(id));
    }
}
