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
  > a,
  a:hover,
  a:active,
  a:visited {
    color: #faeee7;
    text-decoration: none;
  }
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
          프로젝트 소개
          <Item>
            <a href="https://www.youtube.com/watch?v=7s14aXx1vXM">youtube</a>
          </Item>
          <Item>
            <a href="https://codestates.notion.site/39-Team-cbcc6beff32a4ba1bfac9e8a12cf41ad">
              notion
            </a>
          </Item>
        </Box>
        <Box>
          링크
          <Item>
            <a href="https://not-error-064.tistory.com/">팀블로그</a>
          </Item>
          <Item>
            <a href="https://github.com/codestates-seb/seb39_main_056">
              github
            </a>
          </Item>
        </Box>

        <Box>
          약관 및 정책
          <Item>약관보기</Item>
        </Box>
        <Box>
          팀명<Item>NOT ERROR팀</Item>
        </Box>
        <Box>
          문의사항
          <Item>zlcls456@google.com</Item>
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
