import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
// import { axios } from 'axios';

const Container = styled.table`
  width: 100%;
  border-top: 1px solid #444444;
  border-bottom: 1px solid #444444;
  border-collapse: collapse;
`;

const Types = [
  '플렉시테리언',
  '폴로-페스코',
  '페스코',
  '폴로',
  '락토-오보',
  '락토',
  '오보',
  '비건',
  '프루테리언',
];

const Mypage = () => {
  // 맨처음 가져오는 GET 정보 저장된 state
  const [info, setInfo] = useState('');

  const fetchData = () => {
    fetch(`http://localhost:3001/user`)
      .then(res => res.json())
      .then(data => setInfo(data));
  };
  useEffect(() => {
    fetchData();
  }, []);

  const onSubmit = e => {
    e.preventDefault();
    const phone = e.target.phone.value;
    const zipcode = e.target.zipcode.value;
    const firstAdress = e.target.firstAdress.value;
    const secondAdress = e.target.secondAdress.value;
    console.log(phone, zipcode, firstAdress, secondAdress);
  };

  return (
    <form onSubmit={onSubmit}>
      <Container>
        <thead>
          <tr>
            <th></th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>회원정보</td>
            <td>회원이름</td>
            <td>
              <input
                disabled="disabled"
                value={info[0]?.userName === undefined ? '' : info[0]?.userName}
              />
            </td>
          </tr>
          <tr>
            <td></td>
            <td>이메일</td>
            <td>
              <input
                disabled="disabled"
                value={
                  info[0]?.emailAdress === undefined ? '' : info[0]?.emailAdress
                }
              />
            </td>
            {/* <td>
              <input disabled="disabled" value={info[0]?.emailAdress} />
            </td> */}
          </tr>
          <tr>
            <td>배송조회</td>
            <td>전화번호</td>
            <td>
              <input name="phone" type="text" />
            </td>
          </tr>
          <tr>
            <td></td>
            <td>주소</td>
            <td>
              <div>
                <input name="zipcode" type="text" />
                <button>우편번호찾기</button>
              </div>
              <div>
                <input name="firstAdress" type="text" />
                <input name="secondAdress" type="text" />
              </div>
            </td>
          </tr>

          <tr>
            <td></td>
            <td>채식유형</td>
            <td>
              <select>
                {Types.map((type, i) => {
                  return <option key={i}>{type}</option>;
                })}
              </select>
            </td>
          </tr>
          <tr>
            <td></td>
            <td></td>
            <td>
              <button>정보수정</button>
              <button>회원탈퇴</button>
              <Link to="/">
                <button>메인으로</button>
              </Link>
            </td>
          </tr>
        </tbody>
      </Container>
    </form>
  );
};

export default Mypage;
