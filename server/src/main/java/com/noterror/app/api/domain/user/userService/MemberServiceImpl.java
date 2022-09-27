package com.noterror.app.api.domain.user.userService;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.MemberRequestDto;
import com.noterror.app.api.domain.user.repository.MemberRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  // 로직 처리중 에러발생 시 로직 수행 이전 상태로 콜백
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    /** 회원 정보 저장 */
    public Member create(MemberRequestDto userRequestDto){
        Member member = new Member();
        member.setMemberName(userRequestDto.getMemberName());
        member.setEmail(userRequestDto.getEmail());
        member.setPhone(userRequestDto.getPhone());
        member.setCity(userRequestDto.getCity());
        member.setZipCode(userRequestDto.getZipCode());
        member.setDetailAddress(userRequestDto.getDetailAddress());
      //  member.setRole(userRequestDto.getRole());
    //    user.setVegetarianType(userRequestDto.getVegetarianType());
        return memberRepository.save(member);
    }



 //       /** 이미 가입된 회원은 IlligalStateException 예외 발생 */
   // private void validateDuplicateUser(User user){
     //   User findMember = memberRepository.findByEmail(user.getEmail());
       // if(findUser != null) {
         //   throw new IllegalStateException("이미 가입된 회원입니다.");
      //  }
   // }


}
