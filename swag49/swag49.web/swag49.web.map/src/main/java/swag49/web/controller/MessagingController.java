package swag49.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swag49.web.model.MessageDTO;

import javax.validation.Valid;
import java.util.*;

/**
 * @author michael
 */
@Controller
@Scope(value = "session")
@RequestMapping(value = "/messaging")
public class MessagingController {

//    @Autowired
//    private UserController userController;

    private Long idCounter = new Long(0);

    private Map<Long, MessageDTO> messages = new HashMap<Long, MessageDTO>();

    public MessagingController() {
        MessageDTO message = new MessageDTO(new Long(0), new Date(), new Date(), "Test",
                "This is a Test Message", "test", "test");
        messages.put(new Long(0), message);
    }

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {
        //map.put("user", userController.getLoggedInUser());

        return "messaging";
    }

    @RequestMapping(value = "/register")
    public String redirectRegister() {
        return "redirect:../user/register";
    }

    @RequestMapping(value = "/user")
    public String redirectLogin() {
        return "redirect:../user/";
    }

    @RequestMapping(value = "/index")
    public String messaging() {
        return "redirect:./";
    }

    @RequestMapping(value = "/incoming")
    public String incoming(Map<String, Object> map) {
        //if (userController.getLoggedInUser() == null)
        //    return "redirect:./";

        map.put("incomingMessages", getIncomingMessages());

        return "incoming";
    }

    @RequestMapping(value = "/outgoing")
    public String outgoing(Map<String, Object> map) {
        //if (userController.getLoggedInUser() == null)
        //    return "redirect:./";

        map.put("outgoingMessages", getOutgoingMessages());
        map.put("view", true);

        return "outgoing";
    }

    @RequestMapping(value = "/view/{id}")
    public String viewMessage(@PathVariable("id") Long id, Map<String, Object> map) {
        //if (userController.getLoggedInUser() == null)
        //    return "redirect:./";

        map.put("message", messages.get(id));
        map.put("view", true);

        return "message";
    }

    @RequestMapping(value = "/message")
    public String createMessage(Map<String, Object> map) {
         //if (userController.getLoggedInUser() == null)
         //   return "redirect:./";

        map.put("message", new MessageDTO());
        map.put("view", false);

        return "message";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String handleSend(@Valid @ModelAttribute("message")
                               MessageDTO message, BindingResult bingBindingResult,
                               Map<String, Object> map) {

        System.out.println("Received request to send message: " + message);

        if (bingBindingResult.hasErrors()) {
            map.put("view", false);
            return "message";
        }

        sendMessage(message);

        return "redirect:./";
    }

    public List<MessageDTO> getIncomingMessages() {
        List<MessageDTO> messages = new ArrayList<MessageDTO>();

        for(MessageDTO message : this.messages.values()) {
            //if(message.getReceiver().equals(userController.getLoggedInUser().getUsername()))
            //    messages.add(message);
        }

        return messages;
    }

    public List<MessageDTO> getOutgoingMessages() {
        List<MessageDTO> messages = new ArrayList<MessageDTO>();

        for(MessageDTO message : this.messages.values()) {
            //if(message.getSender().equals(userController.getLoggedInUser().getUsername()))
            //    messages.add(message);
        }

        return messages;
    }

    public void sendMessage(MessageDTO message) {
        message.setId(++idCounter);
        //message.setSender(this.userController.getLoggedInUser().getUsername());
        message.setSendDate(new Date());
        messages.put(message.getId(), message);
        System.out.println("Send message:" + message);
    }

}
