package com.laedson.person_api.service;

import com.laedson.person_api.dto.request.MessageResponseDTO;
import com.laedson.person_api.entity.Person;
import com.laedson.person_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created Person with Id " + savedPerson.getId())
                .build();
    }

}
