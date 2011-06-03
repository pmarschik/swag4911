package swag49.web.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author michael
 */
public class MessageDTO {

    private Long id;

	private Date sendDate;

	private Date receiveDate;

	@NotEmpty
    @Size(min = 1, max = 255)
	private String subject;

	@NotEmpty
	private String content;

	private String sender;

	private String receiver;

    public MessageDTO() {
    }

    public MessageDTO(Long id, Date sendDate, Date receiveDate, String subject,
                      String content, String sender, String receiver) {
        this.id = id;
        this.sendDate = sendDate;
        this.receiveDate = receiveDate;
        this.subject = subject;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "sendDate=" + sendDate +
                ", receiveDate=" + receiveDate +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
