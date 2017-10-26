package com.cinek.edziennik.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cinek.edziennik.helpers.JpaResultHelper;
import com.cinek.edziennik.model.Conversation;
import com.cinek.edziennik.model.Message;
import com.cinek.edziennik.repository.ConversationRepository;
@Repository
public class HibernateConversationRepository implements ConversationRepository{
	@PersistenceContext
	EntityManager entityManager;
	@Override
	public List<Conversation> getUserConversations(Long userId) {
		TypedQuery<Conversation> query = entityManager
				.createQuery("Select conv from Conversation conv where conv.user1_id=:uId or conv.user2_id=:uId", Conversation.class);
		query.setParameter("uId", userId);
		List<Conversation> result = query.getResultList();
	    
		return result;
	}

	@Override
	public Conversation getUsersConversation(Long user1_id, Long user2_id) {
		TypedQuery<Conversation> query = entityManager
				.createQuery("Select conv from Conversation conv where (conv.user1_id=:uId1 and conv.user2_id=:uId2) OR (conv.user1_id=:uId2 and conv.user2_id=:uId1) "
						, Conversation.class);
		query.setParameter("uId1", user1_id);
		query.setParameter("uId2", user2_id);
		Conversation result = null;
		
		result = JpaResultHelper.getSingleResultOrNull(query);
		
		return result;
	}

	@Override
	public void insertConversation(Conversation conversation) {
		entityManager.persist(conversation);
		
	}

	@Override
	public void insertMessage(Message message) {
		entityManager.persist(message);
		
	}

	@Override
	public Conversation findConversationById(Long conversationId) {
		Conversation result = entityManager.find(Conversation.class, conversationId);
		return result;
	}

	@Override
	public Message findMessageById(Long messageId) {
		Message result = entityManager.find(Message.class, messageId);
		return result;
	}

}
