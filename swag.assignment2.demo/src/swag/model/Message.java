package swag.model;

@javax.persistence.Entity
public class Message {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	@javax.persistence.Column(nullable = false)
	private java.util.Date sendDate;

	private java.util.Date receiveDate;

	@javax.persistence.Column(nullable = false, length = 255)
	private String subject;

	@javax.persistence.Column(nullable = false)
	private String content;

	@javax.persistence.ManyToOne(optional = false)
	private Player sender;

	@javax.persistence.ManyToOne(optional = false)
	private Player receiver;

	public Message() {
	}

	public Long getId() {
		return id;
	}

	public java.util.Date getSendDate() {
		return sendDate;
	}

	public java.util.Date getReceiveDate() {
		return receiveDate;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public Player getSender() {
		return sender;
	}

	public Player getReceiver() {
		return receiver;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSendDate(java.util.Date sendDate) {
		this.sendDate = sendDate;
	}

	public void setReceiveDate(java.util.Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSender(Player sender) {
		this.sender = sender;
	}

	public void setReceiver(Player receiver) {
		this.receiver = receiver;
	}

}
