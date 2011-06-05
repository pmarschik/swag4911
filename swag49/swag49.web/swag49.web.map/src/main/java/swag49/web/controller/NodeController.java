package swag49.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author michael
 */
@Controller
public class NodeController {

    @Value("$map{map.node.url}")
    private String mapNodeUrl;
    @Value("$map{map.user.node.url}")
    private String userNodeUrl;

    public String getMapNodeUrl() {
        return mapNodeUrl;
    }

    public String getUserNodeUrl() {
        return userNodeUrl;
    }
}
