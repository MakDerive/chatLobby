package com.example.chatlobby.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequest {
	private String roomId;
    private String chatterId;
    private String chatterName;
}
