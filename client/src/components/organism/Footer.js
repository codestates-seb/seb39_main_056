import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  margin-top: 50px;
  background-color: #33272a;
  box-sizing: border-box;
  display: flex;
  justify-content: space-around;
  padding: 30px;
`;

const Box = styled.div`
  /* color: #faeee7; */
  color: #c3f0ca;
  font-family: 'MICEGothic_Bold';
`;

const Item = styled.div`
  /* color: orange; */
  color: #faeee7;
  margin-top: 5px;
`;
const Intro = styled.div`
  background-color: #33272a;
  box-sizing: border-box;
  /* color: #faeee7; */
  color: #c3f0ca;
  text-align: center;
  padding-bottom: 30px;
`;
const Footer = () => {
  return (
    <>
      <Container>
        <Box>
          회사소개
          <Item>오시는길</Item>
          <Item>회사연혁</Item>
        </Box>
        <Box>
          매장안내 <Item>매장찾기</Item>
          <Item>재고안내</Item>
        </Box>

        <Box>
          약관 및 정책
          <Item>약관보기</Item>
        </Box>
        <Box>
          회원사들<Item>회원신청</Item>
        </Box>
        <Box>
          입점문의<Item>James@google.com</Item>
        </Box>
      </Container>
      <Intro>
        🍀채식이들은 여러분의 채식을 응원합니다.
        <br />
        <br />
        copyright 채식이들
      </Intro>
    </>
  );
};

export default Footer;
