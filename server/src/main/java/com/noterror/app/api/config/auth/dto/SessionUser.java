package com.noterror.app.api.config.auth.dto;

import com.noterror.app.api.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { //세션에 사용자 정보를 저장하기 위한 DTO 클래스
    private String name;
    private String email;

    public SessionUser(User user){
        this.name = user.getUserName();
        this.email = user.getEmail();
    }
}
/** User 엔티티가 있음에도 SessionUser를 따로 만드는 이유?
 * User 클래스에는 직렬화를 구현하지 않음
 * User 엔티티 클래스에는 직렬화 코들르 넣지 안흔게 좋음 -> 다른 엔티티와 관계가 형성될 지 모르기 떄문에
 */