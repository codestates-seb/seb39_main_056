package com.noterror.app.api.domain.user.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.IdTokenRequestDto;
import com.noterror.app.api.domain.user.repository.MemberRepository;
import com.noterror.app.infra.filter.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class OAuth2MemberService {

    private final MemberRepository memberRepository;
    private final JWTUtils jwtUtils;
    private final GoogleIdTokenVerifier verifier;

    public OAuth2MemberService(@Value("${app.googleClientId}") String clientId,
                               MemberRepository memberRepository,
                               JWTUtils jwtUtils) {
        this.memberRepository = memberRepository;
        this.jwtUtils = jwtUtils;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public String loginOAuthGoogle(IdTokenRequestDto requestBody) {
        Member member = verifyIDToken(requestBody.getIdToken());
        if(member == null) {
            throw new IllegalStateException();
        }
        member = createOrUpdateUser(member);
        return jwtUtils.createToken(member, false);
    }

    @Transactional
    public Member createOrUpdateUser(Member member) {

        Member existingMember =
                memberRepository
                        .findByEmail(member.getEmail()).orElse(null);
        if (existingMember == null) {
            member.setRole("ROLE_USER");
            memberRepository.save(member);
            return member;
        }
        existingMember.setMemberName(member.getMemberName());
        existingMember.setEmail(member.getEmail());
        memberRepository.save(existingMember);
        return existingMember;
    }

    private Member verifyIDToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();

            String email = payload.getEmail();
            String firstName = (String) payload.get("given_name");
            String lastName = (String) payload.get("family_name");

            return new Member(firstName, lastName, email);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }
}
