package com.noterror.app.api.domain.member.mapper;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-28T14:41:51+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberRequestDtoToMember(MemberRequestDto request) {
        if ( request == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.memberName( request.getMemberName() );
        member.email( request.getEmail() );
        member.phone( request.getPhone() );
        member.zipCode( request.getZipCode() );
        member.city( request.getCity() );
        member.detailAddress( request.getDetailAddress() );

        return member.build();
    }

    @Override
    public MemberResponseDto memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDto.MemberResponseDtoBuilder memberResponseDto = MemberResponseDto.builder();

        memberResponseDto.memberId( member.getMemberId() );
        memberResponseDto.memberName( member.getMemberName() );
        memberResponseDto.email( member.getEmail() );
        memberResponseDto.phone( member.getPhone() );
        memberResponseDto.zipCode( member.getZipCode() );
        memberResponseDto.city( member.getCity() );
        memberResponseDto.detailAddress( member.getDetailAddress() );
        memberResponseDto.vegetarianType( member.getVegetarianType() );
        memberResponseDto.regDate( member.getRegDate() );

        return memberResponseDto.build();
    }
}
