package com.noterror.app.api.domain.user.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.noterror.app.api.domain.entity.User;
import com.noterror.app.api.domain.user.dto.IdTokenRequestDto;
import com.noterror.app.api.domain.user.repository.UserRepository;
import com.noterror.app.infra.filter.JWTUtils;
import lombok.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class OAuth2UserService {

    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final GoogleIdTokenVerifier verifier;

    public OAuth2UserService(@Value("${app.googleClientId}") String clientId,
                          UserRepository userRepository,
                          JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public String loginOAuthGoogle(IdTokenRequestDto requestBody) {
        User user = verifyIDToken(requestBody.getIdToken());
        if(user == null) {
            throw new IllegalStateException();
        }
        user = createOrUpdateUser(user);
        return jwtUtils.createToken(user, false);
    }

    @Transactional
    public User createOrUpdateUser(User user) {

        User existingUser =
                userRepository
                        .findByEmail(user.getEmail()).orElse(null);
        if (existingUser == null) {
            user.setRole("ROLE_USER");
            userRepository.save(user);
            return user;
        }
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
        return existingUser;
    }

    private User verifyIDToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();

            String email = payload.getEmail();
            String firstName = (String) payload.get("given_name");
            String lastName = (String) payload.get("family_name");

            return new User(firstName, lastName, email);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }
}
