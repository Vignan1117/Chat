package com.prashant.chat_app_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prashant.chat_app_backend.entities.Room;

public interface RoomRepository extends MongoRepository<Room, String> {

}