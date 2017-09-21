package com.cinek.edziennik.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
@Entity
public class Conversation {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private Long user1_id;
	@NotNull
	private Long user2_id;
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages;
	
	public Conversation() {
		super();
		messages = new ArrayList<Message>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(Long user1_id) {
		this.user1_id = user1_id;
	}
	public Long getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(Long user2_id) {
		this.user2_id = user2_id;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
    
}
