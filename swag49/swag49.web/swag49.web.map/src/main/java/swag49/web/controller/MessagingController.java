package swag49.web.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import swag49.web.model.MessageDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author michael
 */
@Controller
@Scope(value = "session")
@RequestMapping(value = "/messaging")
public class MessagingController {

    @Autowired
    private MapController mapController;

    @Autowired
    private RestTemplate restTemplate;

    private Map<Long, MessageDTO> messages = Maps.newHashMap();

    private static final String MAP_URL = "http://localhost:8080/map/";

    public MessagingController() {
        MessageDTO message = new MessageDTO("TestSubject", "This is a test message", 1L, "sender", 2L, "receiver",
                new Date(), MAP_URL);
        messages.put(0L, message);
    }

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {
        map.put("user", mapController.getUserID());

        return "messaging";
    }

    @RequestMapping(value = "/register")
    public String redirectRegister() {
        return "redirect:../user/register";
    }

    @RequestMapping(value = "/map")
    public String redirectMap() {
        return "redirect:../map/";
    }

    @RequestMapping(value = "/index")
    public String messaging() {
        return "redirect:./";
    }

    @RequestMapping(value = "/incoming")
    public String incoming(Map<String, Object> map) {
        if (mapController.getUserID() == null)
            return "redirect:./";

        map.put("incomingMessages", getIncomingMessages());

        return "incoming";
    }

    @RequestMapping(value = "/outgoing")
    public String outgoing(Map<String, Object> map) {
        if (mapController.getUserID() == null)
            return "redirect:./";

        map.put("outgoingMessages", getOutgoingMessages());
        map.put("view", true);

        return "outgoing";
    }

    @RequestMapping(value = "/view/{id}")
    public String viewMessage(@PathVariable("id") Long id, Map<String, Object> map) {
        if (mapController.getUserID() == null)
            return "redirect:./";

        map.put("message", messages.get(id));
        map.put("view", true);

        return "message";
    }

    @RequestMapping(value = "/message")
    public String createMessage(Map<String, Object> map) {
        if (mapController.getUserID() == null)
            return "redirect:./";

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

        for (MessageDTO message : this.messages.values()) {
            //if(message.getReceiver().equals(userController.getLoggedInUser().getUsername()))
            messages.add(message);
        }

        return messages;
    }

    public List<MessageDTO> getOutgoingMessages() {
        List<MessageDTO> messages = new ArrayList<MessageDTO>();

        for (MessageDTO message : this.messages.values()) {
            //if(message.getSender().equals(userController.getLoggedInUser().getUsername()))
            messages.add(message);
        }

        return messages;
    }

    public void sendMessage(MessageDTO message) {
        //message.setId(++idCounter);
//        message.setSender(new MessageDTO.UserDTO(userController.getLoggedInUser().getId(),
//                userController.getLoggedInUser().getUsername()));
        // TODO get userid of receiving player
        message.getReceiver().setId(2L);

        message.setSender(new MessageDTO.UserDTO("TODO",1L));
        message.setSent(new Date());
        message.setMapUrl(MAP_URL);
        messages.put(message.getReceiver().getId(), message);

        restTemplate.put("http://localhost:8080/messaging/send", message);
        System.out.println("Send message:" + message);
    }

}
