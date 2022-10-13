import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import InfoTemplate from '../templates/Info/Info';

const Info = () => {
  const [zipcode, setZipcode] = useState('');
  const [address, setAddress] = useState('');

  const navigate = useNavigate();

  const signUp = e => {
    e.preventDefault();
    // console.log(e.target[7].value);
    const userInfo = {
      email: e.target[1].value,
      memberName: e.target[0].value,
      password: e.target[2].value,
      phone: e.target[3].value,
      zipCode: zipcode,
      city: address,
      detailAddress: e.target[7].value,
    };

    const { memberName, email, phoneNum, password } = userInfo;

    if (
      password === '' ||
      email === '' ||
      memberName === '' ||
      zipcode === '' ||
      address === '' ||
      phoneNum === ''
    ) {
      alert('빈칸을 채워주세요!');
    } else {
      axios({
        method: 'post',
        url: `${process.env.REACT_APP_API_URL}/members/sign-up`,
        headers: {
          Accept: 'application/json',
          'Content-type': 'application/json',
        },
        data: JSON.stringify(userInfo),
        // withCredentials: true,
      })
        .then(res => {
          if (res.status === 201) {
            navigate(`/type/${res.data.memberId}`);
          }
        })
        .catch(e => {
          alert('이미 등록된 회원이거나 인터넷 연결이 올바르지 않습니다.');
        });
    }
  };

  return (
    <InfoTemplate
      zipcode={zipcode}
      setZipcode={setZipcode}
      address={address}
      setAddress={setAddress}
      signUp={signUp}
    />
  );
};

export default Info;
