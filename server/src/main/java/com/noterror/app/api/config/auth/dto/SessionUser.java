package com.noterror.app.api.config.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionUser implements Serializable {
    private String name;
    private String email;
}
/** User 엔티티가 있음에도 SessionUser를 따로 만드는 이유?
 * User 클래스에는 직렬화를 구현하지 않음
 * User 엔티티 클래스에는 직렬화 코들르 넣지 안흔게 좋음 -> 다른 엔티티와 관계가 형성될 지 모르기 떄문에
 * 직렬화 기능을 가진 세션 dto를 하나로 추가로 만드는 것도 좋음
 */