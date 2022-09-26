package com.noterror.app.restdocs;

import com.google.gson.Gson;
import com.noterror.app.api.domain.entity.Role;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.controller.MemberController;
import com.noterror.app.api.domain.user.userService.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
public class MemberControllerRestDocs {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @Test
    void postMember() throws Exception {

        //given
        Member memberData = Member.builder()
                .memberId(1L)
                .email("1@do.do")
                .memberName("홍길동")
                .phone("01011112222")
                .city("제주시")
                .zipCode(797979)
                .detailAddress("연동")
                //.role(Role.valueOf("USER"))
                //.vegetarianType("폴로베지테리언")
                .build();

        given(memberService.create(Mockito.any())).willReturn(memberData);

        //when
        mockMvc.perform( //content로 넣어주기
                        post("/oauth/sign-up"))
                .andExpect(status().isCreated())
                .andDo(document("post-user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                List.of(
                                        fieldWithPath("user").type(JsonFieldType.OBJECT).description("회원 데이터"),
                                        fieldWithPath("user.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("user.email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("user.userName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("user.phone").type(JsonFieldType.NUMBER).description("휴대전화번호"),
                                        fieldWithPath("user.city").type(JsonFieldType.STRING).description("주소"),
                                        fieldWithPath("user.zipCode").type(JsonFieldType.STRING).description("우편번호"),
                                        fieldWithPath("user.detailAddress").type(JsonFieldType.STRING).description("상세주소"),
                                        fieldWithPath("user.role").type(JsonFieldType.STRING).description("회원 유형")
                                        // fieldWithPath("user.vegetationType").type(JsonFieldType.STRING).description("채식 유형")
                                )
                        )
                ));
    }
}
