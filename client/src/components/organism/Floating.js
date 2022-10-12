import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  position: fixed;
  bottom: 0;
  right: 0;
  margin: 20px;
  border-radius: 10px;
  background-color: beige;
`;

const Item = styled.div`
  color: #594a4e;
  text-align: center;
  width: 100px;
  height: 30px;
  line-height: 20px;
  padding: 10px;
  :hover {
    text-decoration: underline;
  }
  font-size: x-small;
`;

const Floating = ({ Types, setSearchParams, searchParams }) => {
  const onClick = e => {
    const params = [];
    const paramsObj = {};
    for (let entry of searchParams.entries()) {
      params.push(entry);
    }
    for (let i = 0; i < params.length; i++) {
      paramsObj[`${params[i][0]}`] = params[i][1];
    }
    setSearchParams({
      ...paramsObj,
      page: '1',
      vegetarian: `${e.target.textContent}`,
    });
  };
  return (
    <Container>
      {Types.map((type, i) => {
        return (
          <Item key={i} onClick={onClick}>
            {type}
          </Item>
        );
      })}
    </Container>
  );
};

export default Floating;
