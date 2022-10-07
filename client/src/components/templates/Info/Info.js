import React from 'react';
import Address from '../../organism/Address';
import StyledInput from '../../atom/StyledInput';
import CenterBox from '../../templates/CenterBox';
import * as Styled from './style';

const InfoTemplate = ({ zipcode, address, setZipcode, setAddress, signUp }) => {
  return (
    <CenterBox height="70vh">
      <Styled.InnerBox>
        <form onSubmit={e => signUp(e)}>
          <Styled.Title>회원가입</Styled.Title>

          <Styled.InputContainer>
            <p>이름</p>
            <StyledInput minLength="2" maxLength="5" />
          </Styled.InputContainer>
          <Styled.InputContainer>
            <p>이메일</p>
            <StyledInput type="email" />
          </Styled.InputContainer>
          <Styled.InputContainer>
            <p>비밀번호</p>
            <StyledInput type="password" minLength="8" maxLength="16" />
          </Styled.InputContainer>
          <Styled.InputContainer>
            <p>핸드폰 번호</p>
            <StyledInput
              type="tel"
              pattern="[0-9]{3}[0-9]{4}[0-9]{4}"
              maxLength="11"
              placeholder="- 빼고 번호만 입력해주세요"
            ></StyledInput>
          </Styled.InputContainer>
          <Styled.InputContainer>
            <div>주소</div>
            <Address
              zipcode={zipcode}
              address={address}
              setZipcode={setZipcode}
              setAddress={setAddress}
            />
          </Styled.InputContainer>
          <Styled.SignUpBtn>등록하기</Styled.SignUpBtn>
        </form>
      </Styled.InnerBox>
    </CenterBox>
  );
};

export default InfoTemplate;
