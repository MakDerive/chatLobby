package com.example.chatlobby.service;


import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.chatlobby.model.Chatter;
import com.example.chatlobby.model.JoinRequest;
import com.example.chatlobby.model.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RoomService {
    private Map<String, Room> rooms = new HashMap<>();
    private Random random = new Random();

    public String createRoom(String description) {
        String id = generateRoomId();
        Room room = new Room(id);
        room.setDescription(description);
        rooms.put(id, room);
        return id;
    }


    public Room getRoom(String id) {
        return rooms.get(id);
    }

    
    public Map<String, Room> getAllRooms() {
        return rooms;
    }

    public Room joinRoom(JoinRequest request) {
    	String roomId = request.getRoomId();
        String chatterId = request.getChatterId();
        String chatterName = request.getChatterName();
        Room room = rooms.get(roomId);
        boolean playerExists = room.getPlayers().stream()
                .anyMatch(p -> p.getId().equals(request.getChatterId()));
        if (room != null && !playerExists) {
            Chatter chatter = new Chatter(chatterId, chatterName);
            
            room.addPlayer(chatter);
        }
        return room;
    }
    
    public boolean roomExists(String roomId) {
        return rooms.containsKey(roomId);
    }

    private String generateRoomId() {
        return String.format("%04x", random.nextInt(0xFFFF + 1));
    }
}

