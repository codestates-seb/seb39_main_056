package com.noterror.app.restdocs;

import com.google.gson.Gson;
import com.noterror.app.api.domain.entity.Member;

import com.noterror.app.api.domain.entity.Role;

import org.junit.jupiter.api.DisplayName;
import com.noterror.app.api.domain.member.controller.MemberController;
import com.noterror.app.api.domain.member.userService.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
public class MemberControllerRestDocs {

    @Autowired private MockMvc mockMvc;
    @Autowired private Gson gson;

    @MockBean private MemberService memberService;

    @Test
    @DisplayName("회원 등록 API 문서화")
    void postMember() throws Exception {

        //given
        Member memberDataInDB = Member.builder()
                .memberId(1L)
                .email("1@do.do")
                .memberName("홍길동")
                .phone("01011112222")
                .city("제주시")
                .zipCode("797979")
                .detailAddress("연동")
                .role(Role.valueOf("ROLE_USER"))
                .vegetarianType("폴로베지테리언")
                .regDate(LocalDateTime.now())
                .build();

        Member requestMemberData = Member.builder()
                .email("1@do.do")
                .memberName("홍길동")
                .phone("01011112222")
                .city("제주시")
                .zipCode("797979")
                .detailAddress("연동")
                .build();

        String content = gson.toJson(requestMemberData);

        given(memberService.createMember(Mockito.any())).willReturn(memberDataInDB);

        //when
        ResultActions action =  mockMvc.perform( //content로 넣어주기
                post("/oauth/sign-up")
                .accept(MediaType.APPLICATION_JSON)    //APPLICATION_JSON 형식으로 보내겠다.
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

                action.andExpect(status().isCreated())
                .andDo(document("post-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("memberName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("phone").type(JsonFieldType.NUMBER).description("휴대전화번호"),
                                        fieldWithPath("city").type(JsonFieldType.STRING).description("주소"),
                                        fieldWithPath("zipCode").type(JsonFieldType.STRING).description("우편번호"),
                                        fieldWithPath("detailAddress").type(JsonFieldType.STRING).description("상세주소")
                                        //fieldWithPath("member.role").type(JsonFieldType.STRING).description("회원 유형")
                                        // fieldWithPath("user.vegetationType").type(JsonFieldType.STRING).description("채식 유형")
                                )
                        )
                        /*
                        responseFields(
                                List.of(
                                        fieldWithPath("member").type(JsonFieldType.OBJECT).description("회원 데이터"),
                                        fieldWithPath("member.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("member.email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("member.memberName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("member.phone").type(JsonFieldType.NUMBER).description("휴대전화번호"),
                                        fieldWithPath("member.city").type(JsonFieldType.STRING).description("주소"),
                                        fieldWithPath("member.zipCode").type(JsonFieldType.STRING).description("우편번호"),
                                        fieldWithPath("member.detailAddress").type(JsonFieldType.STRING).description("상세주소"),
                                        fieldWithPath("member.role").type(JsonFieldType.STRING).description("회원 유형")
                                       // fieldWithPath("user.vegetationType").type(JsonFieldType.STRING).description("채식 유형")

                                )
                        )
                         */

                ));
    }
}
