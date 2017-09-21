package com.cinek.edziennik.repository;

import java.util.List;

import com.cinek.edziennik.model.Conversation;
import com.cinek.edziennik.model.Message;

public interface ConversationRepository {
	List<Conversation> getUserConversations(Long userId);
	Conversation getUsersConversation(Long user1_id,Long user2_id);
	void insertConversation(Conversation conversation);
	void insertMessage(Message message);
	Conversation findConversationById(Long conversationId);
	Message findMessageById(Long messageId);
}
