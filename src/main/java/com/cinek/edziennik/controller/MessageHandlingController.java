package com.cinek.edziennik.controller;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinek.edziennik.model.Message;
import com.cinek.edziennik.service.ConversationService;

@Controller
public class MessageHandlingController {
	
	@Autowired
	ConversationService conversationService;
	@MessageMapping("/chat/{receiverId}/{senderId}")
	@SendTo("/topic/messages/{receiverId}/{senderId}")
	public Message send(Message message) throws Exception {
		message.setDate(new Timestamp(System.currentTimeMillis()));
		
		conversationService.createMessage(message.getSender_id(), message.getReceiver_id(), message.getTextContent(),message.getDate());
	    
	    return message;
	}
	
	@RequestMapping("/conversation/{sender_id}/{receiver_id}")
	public String conversationBeetwenUsers(Model model,@PathVariable Long sender_id,@PathVariable Long receiver_id) {
		
	  List<Message> messages =	conversationService.getMessagesBetweenUsers(sender_id, receiver_id);
	  model.addAttribute("messages", messages);
	  model.addAttribute("sender_id",sender_id);
	  model.addAttribute("receiver_id",receiver_id);
		
		
		return "messages";
		
	}

}
