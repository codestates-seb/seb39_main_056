package com.noterror.app.api.domain.user.userService;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.MemberRegistrationRequestDto;
import com.noterror.app.api.domain.user.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional  // 로직 처리중 에러발생 시 로직 수행 이전 상태로 콜백
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;


    public Member create(MemberRegistrationRequestDto memberRequestDto){
        Member member = new Member();
        member.setMemberName(memberRequestDto.getMemberName());
        member.setEmail(memberRequestDto.getEmail());
        member.setPhone(memberRequestDto.getPhone());
        member.setCity(memberRequestDto.getCity());
        member.setZipCode(memberRequestDto.getZipCode());
        member.setDetailAddress(memberRequestDto.getDetailAddress());
      //  member.setRole(memberRequestDto.getRole());
    //    member.setVegetarianType(memberRequestDto.getVegetarianType());
        return memberRepository.save(member);
    }

    public void delete(long memberId) {
        Member findMember = findExistMember(memberId);
        memberRepository.delete(findMember);
    }

    public Member findExistMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember =
                optionalMember
                        .orElseThrow(()-> new NullPointerException("가입된 회원을 찾을 수 없습니다."));
        return findMember;
    }


    //       /** 이미 가입된 회원은 IlligalStateException 예외 발생 */
   // private void validateDuplicateUser(User user){
     //   User findMember = memberRepository.findByEmail(user.getEmail());
       // if(findUser != null) {
         //   throw new IllegalStateException("이미 가입된 회원입니다.");
      //  }
   // }


}
