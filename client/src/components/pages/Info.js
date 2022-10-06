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
    console.log(e.target[0].value);
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

    axios({
      method: 'post',
      url: `${process.env.REACT_APP_URL}`,
      headers: {
        Accept: 'application/type',
        'Content-type': 'application/type',
      },
      data: JSON.stringify(userInfo),
      // withCredentials: true,
    }).then(res => {
      if (res.statusText === 'OK') {
        navigate('/type');
      }
    });

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
