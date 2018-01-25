package com.cinek.edziennik.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinek.edziennik.model.Conversation;
import com.cinek.edziennik.model.Message;
import com.cinek.edziennik.repository.ConversationRepository;
import com.cinek.edziennik.service.ConversationService;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService {
	@Autowired
	ConversationRepository conversationRepository;

	@Override
	public void insertMessageToDatabase(Message message) {
		
		
		Conversation conversation = conversationRepository.getUsersConversation(message.getSender_id(), message.getReceiver_id());
		if (conversation != null) {
			conversation.getMessages().add(message);
			message.setConversation(conversation);
		} else {
			conversation = new Conversation();
			conversation.setUser1_id(message.getSender_id());
			conversation.setUser2_id(message.getReceiver_id());
			conversation.getMessages().add(message);
			message.setConversation(conversation);
			conversationRepository.insertConversation(conversation);
		}

	}
	

	@Override
	public List<Message> getMessagesBetweenUsers(Long user1_id, Long user2_id) {
		Conversation conversation = conversationRepository.getUsersConversation(user1_id, user2_id);
		List<Message> messages = null;
		if (conversation != null) {
			messages = conversation.getMessages();
			Hibernate.initialize(messages);
		}

		return messages;
	}

	@Override
	public void viewMessage(Long messageId) {
		Message message = conversationRepository.findMessageById(messageId);
		message.setViewed(true);

	}

}
