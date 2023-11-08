package com.example.restdemo.Service;

import com.example.restdemo.Repository.PersonRepository;
import com.example.restdemo.model.Message;
import com.example.restdemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.HttpRetryException;
import java.time.LocalDateTime;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public Person addMeesageToPerson(int personId, Message message) {
       if (!repository.existsById(personId)) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
            Person person = repository.findById(personId).get();
            message.setPerson(person);
            message.setTime(LocalDateTime.now());
            person.addMessage(message);
            return repository.save(person);
        }

    public Person deleteMessageFromPerson (int personId, int messageId) {
        if (!repository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Person person = repository.findById(personId).get();
        Message message = person.findMessageById(messageId);

        if (message != null) {
            person.removeMessage(message);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return repository.save(person);
    }

    }
