package com.prashant.chat_app_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.prashant.chat_app_backend.entities.Message;
import com.prashant.chat_app_backend.entities.Room;
import com.prashant.chat_app_backend.repository.RoomRepository;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/{roomId}")
    public Room getRoom(@PathVariable String roomId) {
        return roomRepository.findById(roomId)
         .orElseThrow(() -> new RuntimeException("Room not found"));
    }
    @GetMapping("/{roomId}/messages")
public List<Message> getMessages(@PathVariable String roomId) {

    Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new RuntimeException("Room not found"));

    return room.getMessages();
}
    
}
