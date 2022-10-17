package com.example.reto3.controller;

import com.example.reto3.entities.Message;
import com.example.reto3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    @PostMapping("/all")
    public List<Message> getAll(){
        return messageService.getAll();
    }


    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Message> getAll2(){
        return messageService.getAll();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save (@RequestBody Message costume){
        return messageService.save(costume);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message update(@RequestBody Message message ){
        return messageService.update(message);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return messageService.deleteMessage( id);
    }
}

