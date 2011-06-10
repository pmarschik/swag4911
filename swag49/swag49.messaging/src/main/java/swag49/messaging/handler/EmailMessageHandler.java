package swag49.messaging.handler;

import com.google.common.collect.Maps;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;
import swag49.dao.DataAccessObject;
import swag49.messaging.model.Message;
import swag49.model.User;
import swag49.util.Log;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;

@Component("emailMessageHandler")
public class EmailMessageHandler {
    @Log
    private Logger logger;

    @Autowired
    @Qualifier("messageDAO")
    private DataAccessObject<Message, Long> messageDAO;

    @Autowired
    @Qualifier("userDAO")
    private DataAccessObject<User, String> userDAO;

    @Autowired
    private JavaMailSender mailSender;

    @Value("$messaging{messaging.mail.fromAddress}")
    private String fromAddress;

    @Autowired
    private VelocityEngine velocityEngine;

    @ServiceActivator
    @Transactional
    public void handleMessage(Message message) {
        logger.info("Sending email with content {}", message.getContent());
        message.setReceiveDate(new Date());
        message = messageDAO.create(message);

        User receiverUser = userDAO.get(message.getReceiverUserId());
        User senderUser = userDAO.get(message.getSenderUserId());

        PlayerMessageMimeMessagePreparator messagePreparator =
                new PlayerMessageMimeMessagePreparator(velocityEngine, message, receiverUser, senderUser, fromAddress);

        try {
            mailSender.send(messagePreparator);
        } catch (MailException mailException) {
            logger.warn("Unable to send mail", mailException);
        }
    }

    private static class PlayerMessageMimeMessagePreparator implements MimeMessagePreparator {

        private VelocityEngine velocityEngine;
        private Message message;
        private User receiver;
        private User sender;
        private String from;

        private PlayerMessageMimeMessagePreparator(VelocityEngine velocityEngine, Message message, User receiver,
                                                   User sender, String from) {
            this.velocityEngine = velocityEngine;
            this.message = message;
            this.receiver = receiver;
            this.sender = sender;
            this.from = from;
        }

        @Override
        public void prepare(MimeMessage mimeMessage) throws Exception {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject("[SWAG4911] You've received an in-game message");
            mimeMessageHelper.setTo(receiver.getEmail());
            mimeMessageHelper.setFrom(from);

            Map<String, Object> model = Maps.newHashMap();
            model.put("message", message);
            model.put("receiver", receiver);
            model.put("sender", sender);

            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/message.vm", model);
            mimeMessageHelper.setText(text, true);
        }
    }
}
