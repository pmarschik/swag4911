package swag49.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Date sendDate;

	private Date receiveDate;

	@Column(nullable = false, length = 255)
	private String subject;

	@Column(nullable = false)
	private String content;

	@ManyToOne(optional = false)
	private Player sender;

	@ManyToOne(optional = false)
	private Player receiver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Player getSender() {
		return sender;
	}

	public void setSender(Player sender) {
		this.sender = sender;
	}

	public Player getReceiver() {
		return receiver;
	}

	public void setReceiver(Player receiver) {
		this.receiver = receiver;
	}
	
	
}
