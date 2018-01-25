package com.cinek.edziennik.service;
import java.sql.Timestamp;
import java.util.List;

import com.cinek.edziennik.model.Message;
public interface ConversationService {
	void insertMessageToDatabase(Message message);

	List<Message> getMessagesBetweenUsers(Long user1_id,Long user2_id);
	
	void viewMessage(Long messageId);

}
