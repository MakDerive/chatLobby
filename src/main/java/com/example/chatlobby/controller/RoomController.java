package com.example.chatlobby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.chatlobby.model.Room;
import com.example.chatlobby.service.RoomService;


@Controller
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "index";
    }

    @PostMapping("/create-room")
    public String createRoom(@RequestParam(required = false) String roomDescription) {
        String roomId = roomService.createRoom(roomDescription);
        return "redirect:/room/" + roomId; 
    }
    
    @PostMapping("/join-room")
    public String jointRoom(@RequestParam String roomId) {
    	return "redirect:/room/" + roomId;
    }
    
    @GetMapping("/room/{roomId}")
    public String room(@PathVariable String roomId, Model model) {
        Room room = roomService.getRoom(roomId);
        if (room != null) {
            model.addAttribute("room", room);
            return "room";
        }
        return "redirect:/"; 
    }


}
