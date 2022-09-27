package com.noterror.app.api.domain.member.userService;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.member.dto.MemberPatchDto;
import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.mapper.MemberMapper;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional  // 로직 처리중 에러발생 시 로직 수행 이전 상태로 콜백
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    private final MemberMapper mapper;

    /**
     * 회원 정보 저장
     */
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member mappingMember = mapper.memberRequestDtoToMember(memberRequestDto);
        Member newMember = memberRepository.save(mappingMember);
        MemberResponseDto result = mapper.memberToMemberResponseDto(newMember);
        return result;
    }

    /**
     * 회원 정보 수정
     */
    public MemberResponseDto updateMember(Long memberId, MemberPatchDto memberPatchDto) {

        Member findMember = findExistsMember(memberId);
        findMember.updateProductInfo(memberPatchDto);
        Member updateMember = memberRepository.save(findMember);
        return mapper.memberToMemberResponseDto(updateMember) ;
    }
    /**
     * 회원 정보 조회
     */
    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto findMember(Long memberId){
        return mapper.memberToMemberResponseDto(findExistsMember(memberId));
    }

    public Member findExistsMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember =
                optionalMember
                        .orElseThrow(() -> new NullPointerException("조회된 회원이 없습니다."));
        return findMember;
    }
}



 //       /** 이미 가입된 회원은 IlligalStateException 예외 발생 */
   // private void validateDuplicateUser(User user){
     //   User findMember = memberRepository.findByEmail(user.getEmail());
       // if(findUser != null) {
         //   throw new IllegalStateException("이미 가입된 회원입니다.");
      //  }
   // }


