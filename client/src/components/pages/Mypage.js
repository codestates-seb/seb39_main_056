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

const Mypage = () => {
  // 맨처음 가져오는 GET 정보 저장된 state
  const [info, setInfo] = useState('');
  // 채식유형 저장하는 state
  const [selects, setSelect] = useState();
  // 회원정보에 따라 데이터 고정하는 get요청
  const fetchData = () => {
    fetch(`http://localhost:3001/user`)
      .then(res => res.json())
      .then(data => setInfo(data));
  };
  useEffect(() => {
    fetchData();
  }, []);

  //전역변수 a를 선언
  let a = '';
  //onClick 함수는 어떤버튼을 클릭했는지 확인하는 함수
  const onClick = e => {
    a = e.target.name;
    console.log('내가클릭한버튼은', a);
  };
  //onSubmit함수는 버튼에 따라 함수를 분기
  const onSubmit = e => {
    e.preventDefault();
    if (a === 'updateBtn') {
      console.log('정보수정');
      //input에 있는 정보들을 가져온다.
      const phoneNum = e.target.phone.value;
      const zipCode = e.target.zipcode.value;
      const address = e.target.firstAdress.value;
      const addressDetail = e.target.secondAdress.value;
      const type = { selects }.selects;
      console.log(phoneNum, zipCode, address, addressDetail, type);
      //patch요청을 한다.
      //정보수정: patch요청을 한다.
      if (
        phoneNum === '' ||
        zipCode === '' ||
        address === '' ||
        addressDetail === '' ||
        type === ''
      ) {
        alert('모든 정보를 입력해 주세요');
      } else {
        try {
          fetch(`http://localhost:3001/user`, {
            method: 'PATCH',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
              phoneNum,
              zipCode,
              address,
              addressDetail,
              type,
            }),
          }).then(alert('정보가 성공적으로 수정되었습니다'));
          // .then(window.location.reload());
        } catch (error) {
          console.error(error);
        }
      }
    } else if (a === 'delBtn') {
      console.log('회원탈퇴');
    }
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
                value={info?.userName === undefined ? '' : info?.userName}
              />
            </td>
          </tr>
          <tr>
            <td></td>
            <td>이메일</td>
            <td>
              <input
                disabled="disabled"
                value={info?.emailAdress === undefined ? '' : info?.emailAdress}
              />
            </td>
            {/* 블로깅할거니까 지우지말자 */}
            {/* <td>
              <input disabled="disabled" value={info[0]?.emailAdress} />
            </td> */}
          </tr>
          <tr>
            <td>주문내역조회</td>
            <td>전화번호</td>
            <td>
              <input name="phone" type="text" defaultValue={info.phoneNum} />
            </td>
          </tr>
          <tr>
            <td></td>
            <td>주소</td>
            <td>
              <div>
                <input name="zipcode" type="text" defaultValue={info.zipCode} />
                <button name="zipBtn">우편번호찾기</button>
              </div>
              <div>
                <input
                  name="firstAdress"
                  type="text"
                  defaultValue={info.address}
                />
                <input
                  name="secondAdress"
                  type="text"
                  defaultValue={info.addressDetail}
                />
              </div>
            </td>
          </tr>

          <tr>
            <td></td>
            <td>채식유형</td>
            <td>
              <select
                value={selects}
                defaultValue={info.type}
                onChange={e => setSelect(e.target.value)}
              >
                <option>플렉시테리언</option>
                <option>폴로-페스코</option>
                <option>페스코</option>
                <option>폴로</option>
                <option>락토-오보</option>
                <option>락토</option>
                <option>오보</option>
                <option>비건</option>
                <option>프루테리언</option>
              </select>
            </td>
          </tr>
          <tr>
            <td></td>
            <td></td>
            <td>
              <button name="updateBtn" onClick={onClick}>
                정보수정
              </button>
              <button name="delBtn" onClick={onClick}>
                회원탈퇴
              </button>
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
