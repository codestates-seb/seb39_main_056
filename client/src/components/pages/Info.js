import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Address from '../organism/Address';
import { useNavigate } from 'react-router-dom';
import StyledInput from '../atom/StyledInput';
import CenterBox from '../templates/CenterBox';
import InputContainer from '../organism/InputContainer';
import styled from 'styled-components';
import MainColorBtn from '../atom/MainColorBtn';

const Title = styled.div`
  text-align: center;
  font-size: 1.4rem;
  margin-bottom: 1rem;
`;

const Info = () => {
  const navigate = useNavigate();
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/oauth/user/info`, {
        headers: {
          withCredentials: true,
        },
      })
      .then(data => console.log(data));
  }, []);
  // .then(data => {
  // reponse 오면
  // id랑 email 뽑아내서
  // 변수 할당 후, input에 value값으로 넣어줄 것
  // const id =
  // });

  const [zipcode, setZipcode] = useState('');
  const [address, setAddress] = useState('');

  const signUp = e => {
    e.preventDefault();

    const userInfo = {
      phoneNum: e.target[2].value,
      // 상세주소가 없을경우 서버에 뒤에 빈 칸 하나 붙어서 감
      zipcode: zipcode,
      roadAddress: address,
      // 상세주소
      // 이름
      // 이메일
      // 등등
    };

    axios.post(
      `${process.env.REACT_APP}backendURL`,
      {
        userInfo,
      },
      {
        withCredentials: true,
      },
    );

    if (zipcode === '' || address === '' || userInfo.phoneNum === '') {
      alert('빈칸을 채워주세요!');
    } else {
      axios
        .post(
          `${process.env.REACT_APP_API_URL}/api/auth/google`,
          {
            userInfo,
          },
          {
            Accept: 'application/json',
            'Content-type': 'application/json',
            withCredentials: true,
          },
        )
        .then(res => {
          if (res.ok) {
            navigate('/type');
          }
        });
    }
  };

  return (
    <CenterBox>
      <form onSubmit={e => signUp(e)}>
        <Title>추가정보입력</Title>

        <InputContainer>
          <p>이름 &nbsp; &nbsp;</p>
          <StyledInput disabled />
        </InputContainer>
        <InputContainer>
          <p>이메일</p>
          <StyledInput disabled />
        </InputContainer>
        <InputContainer>
          <p>핸드폰 번호</p>
          <StyledInput
            type="tel"
            pattern="[0-9]{3}[0-9]{4}[0-9]{4}"
            maxLength="11"
            placeholder="- 뺴고 번호만 입력해주세요"
          ></StyledInput>
        </InputContainer>
        <InputContainer>
          <p>주소</p>
          <Address
            zipcode={zipcode}
            address={address}
            setZipcode={setZipcode}
            setAddress={setAddress}
          />
        </InputContainer>
        <MainColorBtn>등록하기</MainColorBtn>
      </form>
    </CenterBox>
  );
};

export default Info;
