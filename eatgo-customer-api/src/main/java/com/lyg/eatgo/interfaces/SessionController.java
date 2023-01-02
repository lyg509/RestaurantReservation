package com.lyg.eatgo.interfaces;

import com.lyg.eatgo.application.UserService;
import com.lyg.eatgo.domain.User;
import com.lyg.eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/session")
    public ResponseEntity<SessionresponseDto> create(
            @RequestBody SessionRequestDto resource
    ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);

       // String accessToken = user.getAccessToken();

        String accessToken = jwtUtil.createToken(user.getId(),user.getName());

        String url = "/session";
        return ResponseEntity.created(new URI(url)).body(
                SessionresponseDto.builder()
                        .accessToken(accessToken)
                        .build());
    }
}
