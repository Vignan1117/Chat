package com.prashant.chat_app_backend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.prashant.chat_app_backend.entities.Message;
import com.prashant.chat_app_backend.entities.Room;
import com.prashant.chat_app_backend.repository.RoomRepository;

@Controller
public class ChatController {

    private final RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {

        // For now, save message to a default room
        Room room = roomRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> {

                    Room newRoom = new Room();

                    newRoom.setName("General Chat");

                    return newRoom;
                });

        room.getMessages().add(message);

        roomRepository.save(room);

        return message;
    }
}