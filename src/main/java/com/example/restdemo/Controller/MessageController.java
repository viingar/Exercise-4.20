package com.example.restdemo.Controller;

import com.example.restdemo.model.Message;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {

    //server.port=8070
    private List<Message> messages = new ArrayList<>(Arrays.asList(
            new Message(1, "Ваше сообщение", "Я сегодня", LocalDateTime.of(2021, 10, 25, 15, 30, 0)),
            new Message(2,"Мое сообщение", " Я завтра", LocalDateTime.of(2023,6,11,18,37,0))
    ));

    @GetMapping("/message")
    public Iterable<Message> getMessage(){
        return messages;
    }

    @PostMapping("/messages")
    public Message addMessage (@RequestBody Message message) {
        messages.add(message);
        return message;
    }

    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return messages.stream().filter(m -> m.getId() == id).findFirst();
    }

    @PutMapping("/messages/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        int index = - 1;
        for (Message m : messages) {
            if (m.getId() == index) {
                index = messages.indexOf(m);
                messages.set(index,message);
            }

        }
        return index == -1 ? addMessage(message) : message;
    }

    @DeleteMapping("/messages/{id}")
    public void DeleteMessage (@PathVariable int id) {
        messages.removeIf(m -> m.getId() == id);
    }
}
