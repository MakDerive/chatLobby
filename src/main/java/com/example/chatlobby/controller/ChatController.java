package com.example.chatlobby.controller;

import com.example.chatlobby.model.chat.ChatMessage;
import com.example.chatlobby.service.RoomService;
import com.example.chatlobby.model.Room;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	
	RoomService roomService;
	
	@Autowired
	public ChatController(RoomService roomService) {
        this.roomService = roomService;
    }

	@MessageMapping("/chat/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage,
            @DestinationVariable String roomId) {
        return chatMessage;
    }

    @MessageMapping("/refreshRooms")
    @SendTo("/topic/rooms")
    public Map<String, Room> refreshRooms() {
        return roomService.getAllRooms();
    }
}
