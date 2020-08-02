package io.github.konieshadow.conf.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.konieshadow.conf.common.util.JsonUtil;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken;
        try (InputStream is = request.getInputStream()) {
            ObjectMapper objectMapper = JsonUtil.objectMapper();
            JSONObject json = objectMapper.readValue(is, JSONObject.class);
            String username = json.getString("username");
            String password = json.getString("password");
            authToken = new UsernamePasswordAuthenticationToken(username, password);
        } catch (IOException exception) {
            exception.printStackTrace();
            authToken = new UsernamePasswordAuthenticationToken("", "");
        }

        setDetails(request, authToken);
        return this.getAuthenticationManager().authenticate(authToken);
    }
}
