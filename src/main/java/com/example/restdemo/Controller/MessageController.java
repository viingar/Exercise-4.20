package com.example.restdemo.Controller;

import com.example.restdemo.model.Message;
import com.example.restdemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.restdemo.Repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {

    //server.port=8070
    @Autowired
    private MessageRepository repository;
    @GetMapping("/message")
    public Iterable<Message> getMessage() {
        return repository.findAll();
    }

    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repository.findById(id);
    }
    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message) {
        repository.save(message);
        return message;
    }
    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        if (status == HttpStatus.OK){
            message.setId(id);
            return new ResponseEntity(repository.save(message), status);
        }
        else
            return new ResponseEntity(repository.save(message), status);
    }
    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        repository.deleteById(id);
    }
}
