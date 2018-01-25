package com.cinek.edziennik.controller;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinek.edziennik.model.Message;
import com.cinek.edziennik.model.User;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.ConversationService;

@Controller
public class MessageHandlingController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ConversationService conversationService;
	@MessageMapping("/chat/{receiverId}/{senderId}")
	@SendTo("/topic/messages/{receiverId}/{senderId}")
	public Message send(Message message) throws Exception {
		message.setDate(new Timestamp(System.currentTimeMillis()));
		message.setViewed(false);
		conversationService.insertMessageToDatabase(message);
	    
	    return message;
	}
	
	@RequestMapping("/conversation/{receiver_id}")
	public String conversationBeetwenUsers(Model model,@PathVariable Long receiver_id) {
	  addUsersToModel(model);
	  List<Message> messages =	conversationService.getMessagesBetweenUsers(getLoggedUserId(), receiver_id);
	  model.addAttribute("messages", messages);
	  model.addAttribute("receiver_id",receiver_id);
	  
		
		
		return "messages";
		
	}
	@RequestMapping("/conversation")
	public String conversationPage(Model model) {
		addUsersToModel(model);
		return "messages";
		
	}
	private void addUsersToModel(Model model) {
		List<User> users = userRepository.getAllUsers();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User thisUser = userRepository.findByUsername(username);
		users.remove(thisUser);
		
		model.addAttribute("users",users);
		model.addAttribute("thisUserId",thisUser.getId());
	}
	private Long getLoggedUserId() {
		List<User> users = userRepository.getAllUsers();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User thisUser = userRepository.findByUsername(username);
		
		return thisUser.getId();
	}

}
