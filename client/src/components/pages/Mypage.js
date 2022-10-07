import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Container = styled.table`
  margin: 20px;
  padding: 10px;
  display: flex;
  justify-content: center;
  flex-flow: row wrap;
  align-items: stretch;
  /* align-items: center; */
  border-top: 1px solid #444444;
  border-bottom: 1px solid #444444;
  border-collapse: collapse;
`;

const Td = styled.td`
  padding: 10px;
  height: 10px;
  flex-grow: 1;
`;

const Input = styled.input`
  width: 100%;
  flex-grow: 1;
  height: 40px;
  margin-bottom: 5px;
  /* box-sizing: border-box; */
`;

const Wrap = styled.div`
  display: flex;
  justify-content: center;
`;

const Btn = styled.button`
  height: 40px;
  margin-left: 3px;
  width: 90px;
`;

const Select = styled.select`
  width: 100%;
  height: 40px;
`;
const Mypage = () => {
  // 맨처음 가져오는 GET 정보 저장된 state
  const [info, setInfo] = useState('');
  // 채식유형 저장하는 state
  const [selects, setSelect] = useState();
  // 회원정보에 따라 데이터 고정하는 get요청
  //setInfo에 데이터 담아주면 됨
  const fetchData = () => {
    let url = `${process.env.REACt_APP_API_URL}/members/info`;
    axios.get(url).then(res => console.log(res));
    // fetch(`http://localhost:3001/user`)
    //   .then(res => res.json())
    //   .then(data => setInfo(data));
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
    let url = `http://localhost:3001/user`;
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
      //정보수정요청을 한다.
      if (
        phoneNum === '' ||
        zipCode === '' ||
        address === '' ||
        addressDetail === '' ||
        type === ''
      ) {
        alert('모든 정보를 입력해 주세요');
      } else {
        //fetch 요청
        try {
          fetch(url, {
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
        } catch (error) {
          console.error(error);
        }
      }
    } else if (a === 'delBtn') {
      if (window.confirm('회원 탈퇴 하시겠습니까?')) {
        fetch(url, {
          method: 'DELETE',
          headers: {
            'Content-type': 'application/json',
          },
        }).then(alert('정상적으로 회원 탈퇴 되었습니다.'));
      }
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
            <Td>
              <Link to="/mypage/:id">회원정보</Link>
            </Td>
            <Td>회원이름</Td>
            <Td>
              <Input
                disabled="disabled"
                value={info?.userName === undefined ? '' : info?.userName}
              />
            </Td>
          </tr>

          <tr>
            <Td>
              <Link to="/order">주문내역조회</Link>
            </Td>
            <Td>이메일</Td>
            <Td>
              <Input
                disabled="disabled"
                value={info?.emailAdress === undefined ? '' : info?.emailAdress}
              />
            </Td>
            {/* 블로깅할거니까 지우지말자 */}
            {/* <Td>
              <input disabled="disabled" value={info[0]?.emailAdress} />
            </Td> */}
          </tr>
          <tr>
            <td></td>

            <Td>전화번호</Td>
            <Td>
              <Input name="phone" type="text" defaultValue={info.phoneNum} />
            </Td>
          </tr>
          <tr>
            <Td></Td>
            <Td>주소</Td>
            <Td>
              <Wrap>
                <Input name="zipcode" type="text" defaultValue={info.zipCode} />
                <Btn name="zipBtn">주소찾기</Btn>
              </Wrap>
              <div>
                <Input
                  name="firstAdress"
                  type="text"
                  defaultValue={info.address}
                />
                <Input
                  name="secondAdress"
                  type="text"
                  defaultValue={info.addressDetail}
                />
              </div>
            </Td>
          </tr>

          <tr>
            <Td></Td>
            <Td>채식유형</Td>
            <Td>
              <Select
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
              </Select>
            </Td>
          </tr>
          <tr>
            <Td></Td>
            <Td></Td>

            <Td>
              <Wrap>
                <Btn name="updateBtn" onClick={onClick}>
                  정보수정
                </Btn>
                <Btn name="delBtn" onClick={onClick}>
                  회원탈퇴
                </Btn>
                <Link to="/">
                  <Btn>메인으로</Btn>
                </Link>
              </Wrap>
            </Td>
          </tr>
        </tbody>
      </Container>
    </form>
  );
};

export default Mypage;
