package com.noterror.app.restdocs;

import com.google.gson.Gson;
import com.noterror.app.api.domain.member.controller.MemberController;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.memberService.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerRestDocs {

    @Autowired MockMvc mockMvc;
    @Autowired Gson gson;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("일반 회원 정보 등록 API 문서화")
    void postMemberApiDocs() throws Exception {
        //given
        SignUpDto signUpDto = new SignUpDto(
                "email@gmail.com",
                "제임스",
                "password1234",
                "01011112222",
                "63336",
                "제주특별자치도 제주시 신촌 6길 6",
                "에코 휴 공간 A동 101호"
        );

        String request = gson.toJson(signUpDto);

        given(memberService.saveMemberInfo(any(SignUpDto.class))).willReturn(1L);

        //when
        mockMvc.perform(
                post("/members/sign-up")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andDo(document("post-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("memberName").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("전화번호"),
                                        fieldWithPath("zipCode").type(JsonFieldType.STRING).description("우편번호"),
                                        fieldWithPath("city").type(JsonFieldType.STRING).description("주소"),
                                        fieldWithPath("detailAddress").type(JsonFieldType.STRING).description("상세주소").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("소셜 회원 정보 등록 API 문서화")
    void postSocialMemberApiDocs() throws Exception {
        //given
        SignUpDto signUpDto = new SignUpDto(
                "social@gmail.com",
                "제임스",
                "01011112222",
                "63336",
                "제주특별자치도 제주시 신촌 6길 6",
                "에코 휴 공간 A동 101호"
        );

        String request = gson.toJson(signUpDto);

        given(memberService.saveMemberInfo(any(SignUpDto.class))).willReturn(1L);

        //when
        mockMvc.perform(
                        post("/members/sign-up")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isCreated())
                .andDo(document("post-social-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("memberName").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("전화번호"),
                                        fieldWithPath("zipCode").type(JsonFieldType.STRING).description("우편번호"),
                                        fieldWithPath("city").type(JsonFieldType.STRING).description("주소"),
                                        fieldWithPath("detailAddress").type(JsonFieldType.STRING).description("상세주소").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("채식 유형 등록 API 문서화")
    void postTypeOfMemberApiDocs() throws Exception {
        //given
        String vegetarianType = "비건";

        MemberResponseDto response = new MemberResponseDto(
                1L,
                "제임스",
                "email@gmail.com",
                "01011114444",
                "63336",
                "제주특별자치도 제주시 신촌 6길 6",
                "에코 휴 공간 A동 101호",
                "비건",
                LocalDateTime.now()
        );

        String request = gson.toJson(vegetarianType);

        given(memberService.saveTypeOfNewMember(anyLong(),any()))
                .willReturn(response);

        //when
        mockMvc.perform(
                        post("/members/sign-up/type/{member-id}",response.getMemberId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isOk())
                .andDo(document("post-type-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("vegetarianType").type(JsonFieldType.STRING).description("채식 유형")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("member").type(JsonFieldType.OBJECT).description("회원 결과 데이터"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("memberName").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("전화번호"),
                                        fieldWithPath("zipCode").type(JsonFieldType.STRING).description("우편번호"),
                                        fieldWithPath("city").type(JsonFieldType.STRING).description("주소"),
                                        fieldWithPath("detailAddress").type(JsonFieldType.STRING).description("상세주소").optional(),
                                        fieldWithPath("vegetarianType").type(JsonFieldType.STRING).description("채식유형")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("회원 정보 수정 API 문성화")
    void putMemberApiDocs() throws Exception {
        //given
        UpdateInfoDto updateInfoDto = new UpdateInfoDto(
                "01011112222",
                "63336",
                "제주특별자치도 제주시 신촌 6길 6",
                "에코 휴 공간 A동 101호",
                "프루테리언"
        );

        MemberResponseDto response = new MemberResponseDto(
                1L,
                "제임스",
                "email@gmail.com",
                "01011114444",
                "63336",
                "제주특별자치도 제주시 신촌 6길 6",
                "에코 휴 공간 A동 101호",
                "프루테리언",
                LocalDateTime.now()
        );

        String request = gson.toJson(updateInfoDto);

        given(memberService.updateMember(anyLong(),any(UpdateInfoDto.class)))
                .willReturn(response);

        //when
        mockMvc.perform(
                        put("/members/info/{member-id}",response.getMemberId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isOk())
                .andDo(document("put-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("전화번호"),
                                        fieldWithPath("zipCode").type(JsonFieldType.STRING).description("우편번호"),
                                        fieldWithPath("city").type(JsonFieldType.STRING).description("주소"),
                                        fieldWithPath("detailAddress").type(JsonFieldType.STRING).description("상세주소").optional(),
                                        fieldWithPath("vegetarianType").type(JsonFieldType.STRING).description("채식유형")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("member").type(JsonFieldType.OBJECT).description("회원 결과 데이터"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("memberName").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("전화번호"),
                                        fieldWithPath("zipCode").type(JsonFieldType.STRING).description("우편번호"),
                                        fieldWithPath("city").type(JsonFieldType.STRING).description("주소"),
                                        fieldWithPath("detailAddress").type(JsonFieldType.STRING).description("상세주소").optional(),
                                        fieldWithPath("vegetarianType").type(JsonFieldType.STRING).description("채식유형")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("회원 탈퇴 API 문서화")
    void deleteMemberApiDocs() throws Exception {

        Long memberId = 1L;

        doNothing().when(memberService).removeMember(anyLong());

        mockMvc.perform(
                        delete("/members/info/{member-id}", memberId))
                .andExpect(status().isNoContent())
                .andDo(document("delete-member",
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        )
                ));
    }
}
