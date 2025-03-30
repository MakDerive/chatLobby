package com.example.chatlobby.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Room {
    private String id;
    private Set<Chatter> players = new HashSet<>();
    private String description;
    public Room(String id) {
        this.id = id;
    }
    
    public void addPlayer(Chatter chatter) {
        players.add(chatter);
    }
    
    
    

    
}
