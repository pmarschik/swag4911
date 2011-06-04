package swag49.web;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swag49.model.User;
import swag49.util.Log;
import swag49.web.model.TokenDTO;

import java.util.Map;
import java.util.UUID;

@Controller
public class TokenService {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Token not found")
    public static class TokenNotFoundException extends Exception {
        public TokenNotFoundException() {
        }

        public TokenNotFoundException(String message) {
            super(message);
        }

        public TokenNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public TokenNotFoundException(Throwable cause) {
            super(cause);
        }
    }

    @Log
    private Logger log;
    private Map<UUID, Long> tokens = Maps.newHashMap();

    public UUID generateToken(User user) {
        try {
            UUID token = UUID.randomUUID();

            tokens.put(token, user.getId());

            return token;
        } catch (Exception ex) {
            log.warn("Unable to generate login token", ex);
            return null;
        }
    }

    public void removeToken(User user) {
        try {
            tokens.remove(user.getId());
        } catch (Exception ex) {
            log.warn("Unable to remove login token", ex);
        }
    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public TokenDTO verifyToken(@PathVariable("token") UUID token) throws TokenNotFoundException {
        Long userId = tokens.get(token);

        if (userId == null)
            throw new TokenNotFoundException("no mapping for token " + token.toString() + " found!");

        return new TokenDTO(token, userId);
    }

}
