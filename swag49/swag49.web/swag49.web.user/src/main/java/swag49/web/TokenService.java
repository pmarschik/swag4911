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
    private Map<UUID, TokenDTO> tokens = Maps.newHashMap();

    public synchronized boolean hasTokenForUser(User user) {

        for (TokenDTO token : tokens.values()) {
            if (token.getUserId().equals(user.getId()))
                return true;
        }

        return false;
    }

    public synchronized UUID generateToken(User user) {
        try {
            UUID token = UUID.randomUUID();

            TokenDTO tokenDTO = new TokenDTO(token, user.getId(), user.getUsername());

            tokens.put(token, tokenDTO);

            return token;
        } catch (Exception ex) {
            log.warn("Unable to generate login token", ex);
            return null;
        }
    }

    public synchronized void removeToken(UUID userToken) {
        try {
            tokens.remove(userToken);
        } catch (Exception ex) {
            log.warn("Unable to remove login token", ex);
        }
    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public TokenDTO verifyToken(@PathVariable("token") UUID token) throws TokenNotFoundException {
        TokenDTO tokenDTO = null;
        synchronized (tokens) {
            tokenDTO = tokens.get(token);
        }

        if (tokenDTO == null)
            throw new TokenNotFoundException("no mapping for token " + token.toString() + " found!");

        return tokenDTO;
    }
}
