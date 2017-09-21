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
	public void createMessage(Long sender_id, Long receiver_id, String textContent, Timestamp timestamp) {
		Message message = new Message();
		message.setSender_id(sender_id);
		message.setReceiver_id(receiver_id);
		message.setTextContent(textContent);
		message.setViewed(false);
		message.setDate(timestamp);
		Conversation conversation = conversationRepository.getUsersConversation(sender_id, receiver_id);
		if (conversation != null) {
			conversation.getMessages().add(message);
			message.setConversation(conversation);
		} else {
			conversation = new Conversation();
			conversation.setUser1_id(sender_id);
			conversation.setUser2_id(receiver_id);
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
