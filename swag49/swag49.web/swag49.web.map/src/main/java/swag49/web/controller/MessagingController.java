package swag49.web.controller;

import com.google.common.collect.Lists;
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
import swag49.web.InternalMessageStore;
import swag49.web.model.MessageDTO;
import swag49.web.model.MessageQueryDTO;
import swag49.web.model.MessageQueryResponse;

import javax.security.auth.Subject;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author michael
 */
@Controller
@Scope("session")
@RequestMapping("/messaging")
public class MessagingController {

    @Autowired
    private NodeContext nodeContext;

    @Autowired
    private MapController mapController;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalMessageStore messageStore;

    private Map<Long, MessageDTO> messages = Maps.newHashMap();

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {
        map.put("user", mapController.getUserName());

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

        map.put("user", mapController.getUserName());
        map.put("incomingMessages", getIncomingMessages());

        return "incoming";
    }

    @RequestMapping(value = "/outgoing")
    public String outgoing(Map<String, Object> map) {
        if (mapController.getUserID() == null)
            return "redirect:./";

        map.put("user", mapController.getUserName());
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

        MessageDTO message = new MessageDTO();
        message.setReceiver(new MessageDTO.UserDTO());
        message.setSender(new MessageDTO.UserDTO());
        map.put("message", message);
        map.put("view", false);

        return "message";
    }

//    @RequestMapping(value = "/send", method = RequestMethod.POST)
//    public String handleSend(@Valid @ModelAttribute("message")
//                             MessageDTO message, BindingResult bingBindingResult,
//                             Map<String, Object> map) {
//
//        System.out.println("Received request to send message: " + message);
//
//        if (bingBindingResult.hasErrors()) {
//            map.put("view", false);
//            return "message";
//        }
//
//        sendMessage(message);
//
//        return "redirect:./";
//    }

//    @RequestMapping(value = "/send", method = RequestMethod.POST)
//    public String handleSend(String dataString) {
//
//        System.out.println("Received request to send message: " + dataString);
//
//
//
//        return "redirect:./";
//    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String handleSend(String username, String subject, String content) {
         MessageDTO newMessage = new MessageDTO();
         MessageDTO.UserDTO user= new MessageDTO.UserDTO(null, username);

        newMessage.setReceiver(user);
        newMessage.setContent(content);
        newMessage.setSubject(subject);

        sendMessage(newMessage);

        return "redirect:./";
    }

    public List<MessageDTO> getIncomingMessages() {
        messages.clear();
        MessageQueryDTO messageQueryDTO = new MessageQueryDTO(nodeContext.getMapNodeUrl(), mapController.getUserID());
        MessageQueryResponse messageQueryResponse =
                restTemplate.postForObject("http://localhost:8080/messaging/get", messageQueryDTO,
                        MessageQueryResponse.class);

        for (MessageDTO message : messageQueryResponse.getMessages()) {
            if (message.getReceiver().getId().equals(mapController.getUserID())) {
                messages.put(message.getId(), message);
            }
        }

        return Lists.newArrayList(messages.values());
    }

    public List<MessageDTO> getOutgoingMessages() {
        messages.clear();
        MessageQueryDTO messageQueryDTO = new MessageQueryDTO(nodeContext.getMapNodeUrl(), mapController.getUserID());
        MessageQueryResponse messageQueryResponse =
                restTemplate.postForObject("http://localhost:8080/messaging/get", messageQueryDTO,
                        MessageQueryResponse.class);

        for (MessageDTO message : messageQueryResponse.getMessages()) {
            if (message.getSender().getId().equals(mapController.getUserID()))
                messages.put(message.getId(), message);
        }

        return Lists.newArrayList(messages.values());
    }

    public void sendMessage(MessageDTO message) {
        message.setSender(new MessageDTO.UserDTO(null, mapController.getUserID()));
        message.setSent(new Date());
        message.setMapUrl(nodeContext.getMapNodeUrl());

        restTemplate.put("http://localhost:8080/messaging/send", message);
        System.out.println("Send message:" + message);
    }

}
